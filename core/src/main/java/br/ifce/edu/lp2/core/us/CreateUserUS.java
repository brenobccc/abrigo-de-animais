package br.ifce.edu.lp2.core.us;

import br.ifce.edu.lp2.core.domain.Usuario;
import br.ifce.edu.lp2.core.domain.UsuarioAdmin;
import br.ifce.edu.lp2.core.ports.driven.repository.SaveUserAdminRepositoryPort;
import br.ifce.edu.lp2.core.ports.driven.repository.SaveUserRepositoryPort;
import br.ifce.edu.lp2.core.ports.driver.CreateUserAdminPort;
import br.ifce.edu.lp2.core.ports.driver.CreateUserPort;

import java.util.List;

//Cria uma história de usuário

public record CreateUserUS(SaveUserRepositoryPort saveUserRepositoryPort)
        implements CreateUserPort {


    @Override//Inserir
    public String apply(Usuario user){
        var id = saveUserRepositoryPort.apply(user);
        return id;
    }



    public List<Usuario> getUsers(){
        return saveUserRepositoryPort.getAll();
    }

    public Usuario getUser(String id){
        return saveUserRepositoryPort.getUser(id);
    }


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
