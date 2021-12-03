package mongodb.adapters;

import br.ifce.edu.lp2.core.domain.UsuarioAdmin;
import br.ifce.edu.lp2.core.ports.driven.repository.SaveUserRepositoryPort;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;


public class SaveUserRepository implements SaveUserRepositoryPort{

    private final MongoClient client;
    MongoOperations mongoOps;
    public SaveUserRepository(){
        ConnectionString connectionString =
                new ConnectionString("mongodb+srv://lp2:kyUyltxpItBHrQIr@lp2-cluster-01.typfq.mongodb.net/admin?retryWrites=true&w=majority");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        client = MongoClients.create(settings);
        mongoOps = new MongoTemplate(client, "lp2");
    }


    @Override
    public String apply(UsuarioAdmin user) {
        var usuarioAdmin = mongoOps.insert(user, "UsuariosAdmins");
        return usuarioAdmin.getId();
    }

    @Override
    public String apply(String id, UsuarioAdmin user) {

        return null;
    }
}