package mongodb.adapters;

import br.ifce.edu.lp2.core.domain.Usuario;
import br.ifce.edu.lp2.core.ports.driven.repository.SaveUserAdminRepositoryPort;
import br.ifce.edu.lp2.core.ports.driven.repository.SaveUserRepositoryPort;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

public class SaveUserRepository implements SaveUserRepositoryPort {

    private final MongoClient client;
    MongoOperations mongoOps;

    //Cria a conexão com o banco de dados MONGODB
    public SaveUserRepository(){
        ConnectionString connectionString =
                new ConnectionString("mongodb+srv://lp2:kyUyltxpItBHrQIr@lp2-cluster-01.typfq.mongodb.net/admin?retryWrites=true&w=majority");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        client = MongoClients.create(settings);
        mongoOps = new MongoTemplate(client, "lp2");
    }

        //Cria
    @Override
    public String apply(Usuario user) {
        List<Usuario> list = mongoOps.findAll(Usuario.class, "Usuarios" );
        boolean controll = false;//verificador se existe email cadastrado

        //percorre a lista e verfica se o email passado já está cadastrado
        for(Usuario admin : list){
            if(admin.getNome().equals(user.getNome())) {
                //se tiver ele irá setar o controll como "true"
                controll = true;
            }
        }
        if(controll){//se controll for true, é porque tem um email igual cadastrado
            System.out.println("Usuario já está cadastrado");
            return "Usuario já está cadastrado";
        }else{//Se não, irá ser permitido inserir o novo user no MOGODB
            var usuario = mongoOps.insert(user, "Usuarios");
            System.out.println("Salvo no Banco de Dados com sucesso!");
            return usuario.getId();
        }
    }


     public List<Usuario> getAll(){
        return mongoOps.findAll(Usuario.class, "Usuarios" );
    }

     public Usuario getUser(String _id){
        return mongoOps.findById(_id, Usuario.class, "Usuarios");
    }
}