package br.ifce.edu.lp2.core.us;

import br.ifce.edu.lp2.core.domain.UsuarioAdmin;
import br.ifce.edu.lp2.core.ports.driven.repository.SaveUserAdminRepositoryPort;
import br.ifce.edu.lp2.core.ports.driver.CreateUserAdminPort;

//Cria uma história de usuário

public record CreateUserAdminUS(SaveUserAdminRepositoryPort saveUserAdminRepositoryPort)
        implements CreateUserAdminPort {


    @Override//Inserir
    public String apply(UsuarioAdmin user){
        var id = saveUserAdminRepositoryPort.apply(user);
        return id;
    }

    //public List<UsuarioAdmin> getUsers(){
//   // }
    //Deletar
    public String apply(String id){
        return saveUserAdminRepositoryPort.apply(id);
    }

    public String applyUpdate(String id, String senha){
        return saveUserAdminRepositoryPort.applyUpdate(id,senha);
    }
}
