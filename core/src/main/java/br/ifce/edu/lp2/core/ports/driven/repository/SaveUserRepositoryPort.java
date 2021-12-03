package br.ifce.edu.lp2.core.ports.driven.repository;

import br.ifce.edu.lp2.core.domain.Usuario;
import br.ifce.edu.lp2.core.domain.UsuarioAdmin;
import org.springframework.data.mongodb.repository.MongoRepository;

//cria a porta para criar um usuário
public interface SaveUserRepositoryPort{
    //cadastro o cliente e retorno o id
    String apply(UsuarioAdmin user);

    String apply(String id, UsuarioAdmin user);
}
