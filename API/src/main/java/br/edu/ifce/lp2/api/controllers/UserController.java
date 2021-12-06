package br.edu.ifce.lp2.api.controllers;

import br.ifce.edu.lp2.core.domain.Animal;
import br.ifce.edu.lp2.core.domain.Usuario;
import br.ifce.edu.lp2.core.domain.UsuarioAdmin;
import br.ifce.edu.lp2.core.us.CreateUserAdminUS;
import br.ifce.edu.lp2.core.us.CreateUserUS;
import mongodb.adapters.SaveUserAdminRepository;
import mongodb.adapters.SaveUserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "/usuarios/")
public class UserController {

    private static final SaveUserRepository repo = new SaveUserRepository();
    private static final CreateUserUS port = new CreateUserUS(repo);

   @PostMapping//Criando um usuário(passo o nome e senha)
    public String createUser(@RequestParam String nome , @RequestParam String telefone) {
        Usuario user = new Usuario();//Cria um usuário

        user.setTelefone(telefone);//atribui a senha
        user.setNome(nome);//atribui o nome

        List<Animal> an = new ArrayList<Animal>();
        user.setAnimais(an);//lista de animais nula

        var id = port.apply(user);//mando o meu usuário pro repositório e pego o ID

        return id;//retorna o ID do usuário cadaastrado
    }

    @GetMapping//Criando um usuário(passo o nome e senha)
    public List<Usuario> getUsers() {
        return port.getUsers();//mando o meu usuário pro repositório e pego o ID
    }


    @GetMapping(value = "usuario/")
    public Usuario getUser(@RequestParam String id){
        return repo.getUser(id);
    }
}



