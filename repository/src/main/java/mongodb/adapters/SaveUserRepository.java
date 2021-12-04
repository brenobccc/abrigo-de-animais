package mongodb.adapters;

import br.ifce.edu.lp2.core.domain.UsuarioAdmin;
import br.ifce.edu.lp2.core.ports.driven.repository.SaveUserRepositoryPort;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.internal.operation.UpdateOperation;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;


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
        List<UsuarioAdmin> list = mongoOps.findAll(UsuarioAdmin.class, "UsuariosAdmins" );


        boolean controll = false;

        for(UsuarioAdmin admin : list){
           if(admin.getEmail().equals(user.getEmail())) {
               controll = true;
           }
        }


       /* list.stream().map((usuario)->{
            System.out.println(usuario.getEmail());
            return null;
        });*/

        if(controll){
            System.out.println("email já está cadastrado");
            return "email já está cadastrado";
        }else{
            var usuarioAdmin = mongoOps.insert(user, "UsuariosAdmins");
            System.out.println("Salvo no Banco de Dados com sucesso!");
            return usuarioAdmin.getId();
        }

    }

   /* public List<UsuarioAdmin> getAllUsers(){
        return mongoOps.findAll(UsuarioAdmin.class, "UsuariosAdmins" );
    }*/

    @Override
    public String apply(String _id, String senha) {
        Query query = Query.query(Criteria.where("_id").is(_id));

        if(mongoOps.findById(_id, UsuarioAdmin.class, "UsuariosAdmins") != null){
            mongoOps.findAndRemove( query, UsuarioAdmin.class, "UsuariosAdmins");
            return "Usuário excluido com sucesso";
        }
        return "Usuário não encontrado!!";
    }


}