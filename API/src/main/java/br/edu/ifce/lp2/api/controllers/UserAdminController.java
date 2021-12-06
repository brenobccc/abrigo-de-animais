package br.edu.ifce.lp2.api.controllers;

import br.ifce.edu.lp2.core.domain.UsuarioAdmin;
import br.ifce.edu.lp2.core.us.CreateUserAdminUS;
import mongodb.adapters.SaveUserAdminRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/")
public class UserAdminController {
    private static final SaveUserAdminRepository repo = new SaveUserAdminRepository();
    private static final CreateUserAdminUS port = new CreateUserAdminUS(repo);

    @PostMapping//Criando um usuário(passo o nome e senha)
    public String createUserAdmin(@RequestParam String nome , @RequestParam String senha) {
        UsuarioAdmin user = new UsuarioAdmin();//Cria um usuário

        user.setEmail(nome);//atribui o nome
        user.setSenha(senha);//atribui a senha
        var id = port.apply(user);//mando o meu usuário pro repositório e pego o ID

        return id;//retorna o ID do usuário cadaastrado
    }

    @DeleteMapping//Deletando o usuário
    public String deleteUserAdmin(@RequestParam String id) {
        return port.apply(id);//Retorna uma mensagem informando
        // se foi cadastrado com sucesso ou não.
    }

    @PutMapping//atualizando usuario
    public String updateUserAdmin(@RequestParam String id, @RequestParam String senha){
        return port.applyUpdate(id,senha);
    }

    @GetMapping
    public List<UsuarioAdmin> getUsuarios(){
        return repo.getAll();
    }

    @GetMapping(value = "usuario/")
    public UsuarioAdmin getUser(@RequestParam String id){
        return repo.getUser(id);
    }
}
