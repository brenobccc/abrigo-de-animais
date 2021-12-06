package br.ifce.edu.lp2.core.us;

import br.ifce.edu.lp2.core.domain.Animal;
import br.ifce.edu.lp2.core.domain.Usuario;
import br.ifce.edu.lp2.core.ports.driven.repository.SaveAnimalRepositoryPort;
import br.ifce.edu.lp2.core.ports.driven.repository.SaveUserRepositoryPort;
import br.ifce.edu.lp2.core.ports.driver.CreateAnimalPort;
import br.ifce.edu.lp2.core.ports.driver.CreateUserPort;

import java.util.List;

//Cria uma história de usuário

public record CreateAnimalUS(SaveAnimalRepositoryPort saveAnimalRepositoryPort)
        implements CreateAnimalPort {


    @Override//Doar animal
    public String apply(Animal animal){
        var id = saveAnimalRepositoryPort.apply(animal);
        return id;
    }

    public String apply(String id_animal, String id_usuario){
        var id = saveAnimalRepositoryPort.apply(id_animal,id_usuario);
        return id;
    }

    public List<Animal> getAll(){
        return saveAnimalRepositoryPort.getAll();
    }

    public Animal getAnimal(String id){
        return saveAnimalRepositoryPort.getAnimal(id);
    }


    /*
    public List<Usuario> getUsers(){
        return saveUserRepositoryPort.getAll();
    }

    public Usuario getUser(String id){
        return saveUserRepositoryPort.getUser(id);
    }
    */

    //public List<UsuarioAdmin> getUsers(){
//   // }
    //Deletar
     /*
    public String apply(String id){
        return saveUserRepositoryPort.apply(id);
    }

    public String applyUpdate(String id, String senha){
        return saveUserRepositoryPort.applyUpdate(id,senha);
    }*/
}
