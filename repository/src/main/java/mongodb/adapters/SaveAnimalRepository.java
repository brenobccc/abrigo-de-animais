package mongodb.adapters;

import br.ifce.edu.lp2.core.domain.Animal;
import br.ifce.edu.lp2.core.domain.Status;
import br.ifce.edu.lp2.core.domain.Usuario;
import br.ifce.edu.lp2.core.domain.UsuarioAdmin;
import br.ifce.edu.lp2.core.ports.driven.repository.SaveAnimalRepositoryPort;
import br.ifce.edu.lp2.core.ports.driven.repository.SaveUserRepositoryPort;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.Query;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SaveAnimalRepository implements SaveAnimalRepositoryPort {

    private final MongoClient client;
    MongoOperations mongoOps;

    //Cria a conexão com o banco de dados MONGODB
    public SaveAnimalRepository(){
        ConnectionString connectionString =
                new ConnectionString("mongodb+srv://lp2:kyUyltxpItBHrQIr@lp2-cluster-01.typfq.mongodb.net/admin?retryWrites=true&w=majority");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        client = MongoClients.create(settings);
        mongoOps = new MongoTemplate(client, "lp2");
    }

        //Cria
    public String apply(Animal animal) {
        var pet = mongoOps.insert(animal, "Animais");
        return  pet.getId();
    }



    public List<Animal> getAll(){
        return mongoOps.findAll(Animal.class, "Animais" );
    }

    public Animal getAnimal(String id){
        return mongoOps.findById(id,Animal.class, "Animais" );
    }

    //Adotar
    public String apply(String id_animal, String id_usuario) {
        Usuario usuario = mongoOps.findById(id_usuario, Usuario.class, "Usuarios");
        Animal animal = mongoOps.findById(id_animal, Animal.class, "Animais");

        if(usuario!=null && animal!=null && !animal.getStatus_animal().equals(Status.ADOTADO)){
            List<Animal> lista = new ArrayList<Animal>();
            animal.setStatus_animal(animal.verificarStatus(3));
            lista.add(animal);

            Update update_animal = new Update();
            update_animal.set("Animais",lista);

            Update update_animal_status = new Update();
            update_animal_status.set("status_animal", "ADOTADO");
            mongoOps.findAndModify(new Query(Criteria.where("_id").is(id_animal)), update_animal_status, Animal.class, "Animais");
            mongoOps.findAndModify(new Query(Criteria.where("_id").is(id_usuario)), update_animal, Usuario.class, "Usuarios");

            return " Adotado por:"+usuario.getNome()+" ";
        }
        return "Dados inválidos";
    }


    public String devolverAnimal(String id_animal, String id_usuario) {
        Usuario usuario = mongoOps.findById(id_usuario, Usuario.class, "Usuarios");
        Animal animal = mongoOps.findById(id_animal, Animal.class, "Animais");

        if(usuario!=null && animal!=null && animal.getStatus_animal().equals(Status.ADOTADO)){
            List<Animal> arrayAnimais = usuario.getAnimais();
            boolean animal_exist = false;
            Animal animal_reservar = null;
            int index = 1;
            int index_personalizado =0;
            for(Animal pet : arrayAnimais) {

                if (pet.getId().equals(id_animal)) {
                    animal_exist = true;
                  //  animal_reservar = pet;
                    index_personalizado = index;
                    System.out.println("acheiiiiiii o " + pet.getNome());
                }

                index = index+1;
            }
            if(animal_exist){
                Animal am = mongoOps.findById(id_animal, Animal.class, "Animais");
                Query query = Query.query(Criteria.where("_id").is(id_animal));//query para filtro
                Query query_usuario = Query.query(Criteria.where("_id").is(id_usuario));
                Update update = new Update();
                update.set("status_animal",Status.DISPONIVEL);
               /* System.out.println("index:"+index_personalizado);
                System.out.println("array"+ Arrays.toString(arrayAnimais.toArray()));
                System.out.println("birrrl o " + arrayAnimais.toString());
                System.out.println("2"+arrayAnimais.size());*/
                arrayAnimais.remove(index_personalizado-1);
               /* System.out.println("birrrl o " + arrayAnimais.toString());
                System.out.println("2"+arrayAnimais.size());
*/

                Update update2 = new Update();
                update2.set("Animais",arrayAnimais);
                mongoOps.findAndModify( query, update,Animal.class, "Animais");
                mongoOps.findAndModify( query_usuario, update2,Usuario.class, "Usuarios");
                return "Animal devolvido com sucesso";
            }else{
                return "O usuário: "+usuario.getNome()+", com o ID: "+usuario.getId()+
                        ", Não contem o animal "+ animal.getId()+" na sua lista";
            }


        }
        return "Dados inválidos";
    }






    //DELETAR
    public String applyDelete(String _id) {

        Animal animal = mongoOps.findById(_id, Animal.class, "Animais");
        Query query = Query.query(Criteria.where("_id").is(_id));//query para filtro
        if (animal != null) {
            if (animal.getStatus_animal() != Status.ADOTADO) {
                mongoOps.findAndRemove(query, Animal.class, "Animais");
                return "Animal " + animal.getNome() + " com o id " + animal.getId() + " foi excluido com sucesso.";
            } else {
                return "Exclusão inválida! O animal está Adotado e só pode ser excluido em caso de devolvimento do pet";
            }
        }
        return "Animal não encontrado";
    }
}


 /*    public List<Animal> getAll(){
        return mongoOps.findAll(Animal.class, "Usuarios" );
    }

    public Usuario getUser(String _id){
        return mongoOps.findById(_id, Animal.class, "Usuarios");
    }*/
 /*

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
            return "Usuário alterado com sucesso";//mensagem de sucesso
        }
        return "Usuário não existe";
    }

    @Override//Função para DELETAR usuário
    public String apply(String _id) {
        Query query = Query.query(Criteria.where("_id").is(_id));//query para filtro

        //se ele encontrar algo, irá entrar na condicao
        if(mongoOps.findById(_id, UsuarioAdmin.class, "UsuariosAdmins") != null){
            //irá encontrar e remover o osuário segundo a condição(query de _id = id);
            mongoOps.findAndRemove( query, UsuarioAdmin.class, "UsuariosAdmins");
            return "Usuário excluido com sucesso";//mensagem de sucesso
        }
        return "Usuário não encontrado!!";//mensagem de falha
    }

    */
