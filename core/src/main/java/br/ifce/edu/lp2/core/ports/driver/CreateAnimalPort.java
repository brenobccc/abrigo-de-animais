package br.ifce.edu.lp2.core.ports.driver;

import br.ifce.edu.lp2.core.domain.Animal;
import br.ifce.edu.lp2.core.domain.UsuarioAdmin;

//cria a porta para criar um usuário
public interface CreateAnimalPort {

    //cadastro o cliente e retorno o id
    String apply(Animal user);

}
