package br.ifce.edu.lp2.core.us;

import br.ifce.edu.lp2.core.domain.Usuario;
import br.ifce.edu.lp2.core.domain.UsuarioAdmin;
import br.ifce.edu.lp2.core.ports.driven.repository.SaveUserRepositoryPort;
import br.ifce.edu.lp2.core.ports.driver.CreateUserPort;

import java.util.List;

//Cria uma história de usuário

public record CreateUserUS(SaveUserRepositoryPort saveUserRepositoryPort)
        implements CreateUserPort{


    @Override//Inserir
    public String apply(UsuarioAdmin user){
        var id = saveUserRepositoryPort.apply(user);
        return id;
    }

    //public List<UsuarioAdmin> getUsers(){
//   // }
    //Deletar
    public String apply(String id){
        return saveUserRepositoryPort.apply(id);
    }

    public String applyUpdate(String id, String senha){
        return saveUserRepositoryPort.applyUpdate(id,senha);
    }
}
