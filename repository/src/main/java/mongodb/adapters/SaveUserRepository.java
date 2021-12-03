package mongodb.adapters;


import br.ifce.edu.lp2.core.domain.Usuario;
import br.ifce.edu.lp2.core.domain.UsuarioAdmin;
import br.ifce.edu.lp2.core.ports.driven.repository.SaveUserRepositoryPort;

import com.mongodb.BasicDBObject;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.ClientSession;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.conversions.Bson;
import org.bson.json.JsonObject;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SaveUserRepository implements SaveUserRepositoryPort{

    //private MongoRepository reposi;
    //private final MongoTemplate operations;
    private final MongoClient client;
    MongoOperations mongoOps;
    public SaveUserRepository(){
        ConnectionString connectionString =
                new ConnectionString("mongodb+srv://lp2:kyUyltxpItBHrQIr@lp2-cluster-01.typfq.mongodb.net/admin?retryWrites=true&w=majority");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        //System.out.println("In that way: "+ (!this.client.getDatabase("lp2").equals(null) ? "ok" : "notok")+"\n|||");
        client = MongoClients.create(settings);
       mongoOps = new MongoTemplate(client, "lp2");
           //MongoClient client = MongoClients.create("mongodb+srv://lp2:19444@lp2-cluster-01.typfq.mongodb.net/abrigoanimais?retryWrites=true&w=majority");
        //spring.data.mongodb.uri=mongodb+srv://lp2:19444@cluster0.kjzyv.mongodb.net/lp2
      //  operations = new MongoTemplate(client, "abrigoanimais");

    }


    @Override
    public String apply(UsuarioAdmin user) {
        //operations.insert(user)
        //List<UsuarioAdmin> usuarios = new ArrayList<>();
        mongoOps.insert(user, "UsuariosAdmins");

        //mongoOps.getCollection("publishers").createIndex((teste));
        //System.out.println("||||||||\n"+mongoOps.collectionExists("publisher")+"\n||||||||");
        return "client.getDatabase();";
    }

    @Override
    public String apply(String id, UsuarioAdmin user) {
        return null;
    }
}