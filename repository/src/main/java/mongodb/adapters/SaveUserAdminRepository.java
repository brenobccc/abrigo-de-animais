package mongodb.adapters;

import br.ifce.edu.lp2.core.domain.UsuarioAdmin;
import br.ifce.edu.lp2.core.ports.driven.repository.SaveUserAdminRepositoryPort;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public class SaveUserAdminRepository implements SaveUserAdminRepositoryPort {

    private final MongoClient client;
    MongoOperations mongoOps;

    //Cria a conexão com o banco de dados MONGODB
    public SaveUserAdminRepository(){
        ConnectionString connectionString =
                new ConnectionString("mongodb+srv://lp2:kyUyltxpItBHrQIr@lp2-cluster-01.typfq.mongodb.net/admin?retryWrites=true&w=majority");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        client = MongoClients.create(settings);
        mongoOps = new MongoTemplate(client, "lp2");
    }


    @Override//Cria o usuário
    public String apply(UsuarioAdmin user) {
        List<UsuarioAdmin> list = mongoOps.findAll(UsuarioAdmin.class, "UsuariosAdmins" );
        boolean controll = false;//verificador se existe email cadastrado

        //percorre a lista e verfica se o email passado já está cadastrado
        for(UsuarioAdmin admin : list){
           if(admin.getEmail().equals(user.getEmail())) {
               //se tiver ele irá setar o controll como "true"
               controll = true;
           }
        }
        if(controll){//se controll for true, é porque tem um email igual cadastrado
            System.out.println("email já está cadastrado");
            return "email já está cadastrado";
        }else{//Se não, irá ser permitido inserir o novo user no MOGODB
            var usuarioAdmin = mongoOps.insert(user, "UsuariosAdmins");
            System.out.println("Salvo no Banco de Dados com sucesso!");
            return usuarioAdmin.getId();
        }
    }

    public List<UsuarioAdmin> getAll(){
        return mongoOps.findAll(UsuarioAdmin.class, "UsuariosAdmins" );
    }

    public UsuarioAdmin getUser(String _id){
        return mongoOps.findById(_id, UsuarioAdmin.class, "UsuariosAdmins");
    }

    //Funcao para ALTERAR usuário
    public String applyUpdate(String _id, String senha){
        Query query = Query.query(Criteria.where("_id").is(_id));//query para filtro
        Update update = new Update();
        update.set("senha",senha);
        //se ele encontrar algo, irá entrar na condicao
        if(mongoOps.findById(_id, UsuarioAdmin.class, "UsuariosAdmins") != null){
            //irá encontrar e remover o osuário segundo a condição(query de _id = id);
            mongoOps.findAndModify( query, update,UsuarioAdmin.class, "UsuariosAdmins");
            return "Usuário Admin alterado com sucesso";//mensagem de sucesso
        }
        return "Usuário Admin não existe";
    }

    @Override//Função para DELETAR usuário
    public String apply(String _id) {
        Query query = Query.query(Criteria.where("_id").is(_id));//query para filtro

        //se ele encontrar algo, irá entrar na condicao
        if(mongoOps.findById(_id, UsuarioAdmin.class, "UsuariosAdmins") != null){
            //irá encontrar e remover o osuário segundo a condição(query de _id = id);
            mongoOps.findAndRemove( query, UsuarioAdmin.class, "UsuariosAdmins");
            return "Usuário Admin excluido com sucesso";//mensagem de sucesso
        }
        return "Usuário Admin não encontrado!!";//mensagem de falha
    }


}