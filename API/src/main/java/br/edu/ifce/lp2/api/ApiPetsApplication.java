package br.edu.ifce.lp2.api;

import br.ifce.edu.lp2.core.domain.UsuarioAdmin;
import br.ifce.edu.lp2.core.us.CreateUserUS;
import mongodb.adapters.SaveUserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
@RequestMapping(value = "/")
public class ApiPetsApplication {

	private static final SaveUserRepository repo = new SaveUserRepository();
	private static final CreateUserUS port = new CreateUserUS(repo);

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


	//Funcao principal para TESTES
	public static void main(String[] args) {
		SpringApplication.run(ApiPetsApplication.class, args);
		System.out.println("||||| API RUNNING |||||");
	}

}
