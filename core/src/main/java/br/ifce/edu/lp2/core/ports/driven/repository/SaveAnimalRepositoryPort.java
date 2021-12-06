package br.ifce.edu.lp2.core.ports.driven.repository;

import br.ifce.edu.lp2.core.domain.Animal;
import br.ifce.edu.lp2.core.domain.Usuario;

import java.util.List;

//cria a porta para criar um usuário
public interface SaveAnimalRepositoryPort {
    //cadastro o cliente e retorno o id
    String apply(Animal animal);
    String apply(String id_animal, String id_usuario);
    List<Animal> getAll();
    Animal getAnimal(String id);
}
