package br.ifce.edu.lp2.core.us;

import br.ifce.edu.lp2.core.domain.Usuario;
import br.ifce.edu.lp2.core.ports.driven.repository.SaveUserRepositoryPort;
import br.ifce.edu.lp2.core.ports.driver.CreateUserPort;

//Cria uma história de usuário

public record CreateUserUS(SaveUserRepositoryPort saveUserRepositoryPort)
        implements CreateUserPort{

    @Override
    public String apply(Usuario user){

        //VERIFICAR SE EXISTE ALGUM COM O ID IGUAL
        //SALVAR NO BD
            var id = saveUserRepositoryPort.apply(user);
            System.out.println("Salvo no Banco de Dados");
            return id;
    }

}
