package br.ifce.edu.lp2.core.ports.driven.repository;

import br.ifce.edu.lp2.core.domain.Usuario;
import br.ifce.edu.lp2.core.domain.UsuarioAdmin;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

//cria a porta para criar um usuário
public interface SaveUserRepositoryPort{
    //cadastro o cliente e retorno o id
    String apply(UsuarioAdmin user);

    String apply(String id, String senha);
  //  String applyUpdate(String id, String senha);

    //List<UsuarioAdmin> getAllUsers();
}
