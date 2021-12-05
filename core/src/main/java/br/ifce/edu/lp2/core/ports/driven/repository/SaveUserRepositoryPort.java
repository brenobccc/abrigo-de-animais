package br.ifce.edu.lp2.core.ports.driven.repository;

import br.ifce.edu.lp2.core.domain.UsuarioAdmin;

import java.util.List;

//cria a porta para criar um usuário
public interface SaveUserRepositoryPort{
    //cadastro o cliente e retorno o id
    String apply(UsuarioAdmin user);
    String apply(String id);
    String applyUpdate(String id, String senha);
    List<UsuarioAdmin> getAll();
    UsuarioAdmin getUser(String id);
}
