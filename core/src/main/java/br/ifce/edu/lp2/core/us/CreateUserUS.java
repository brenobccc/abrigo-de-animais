package br.ifce.edu.lp2.core.us;

import br.ifce.edu.lp2.core.domain.Usuario;
import br.ifce.edu.lp2.core.ports.driver.CreateUserPort;

import java.util.UUID;

//Cria uma história de usuário

public class CreateUserUS implements CreateUserPort{

    @Override
    public String apply(Usuario user){
        System.out.println("Salvar no banco de dados");
        return UUID.randomUUID().toString();
    }

}
