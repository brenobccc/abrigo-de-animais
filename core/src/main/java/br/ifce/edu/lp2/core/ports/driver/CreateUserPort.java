package br.ifce.edu.lp2.core.ports.driver;

import br.ifce.edu.lp2.core.domain.Usuario;

//cria a porta para criar um usuário
public interface CreateUserPort {

    //cadastro o cliente e retorno o id
    String apply(Usuario user);

}
