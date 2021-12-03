package br.edu.ifce.lp2.api;

import br.ifce.edu.lp2.core.domain.Usuario;
import br.ifce.edu.lp2.core.domain.UsuarioAdmin;
import br.ifce.edu.lp2.core.ports.driver.CreateUserPort;
import br.ifce.edu.lp2.core.us.CreateUserUS;
import mongodb.adapters.SaveUserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
@RequestMapping(value = "/")
public class ApiPetsApplication {

	private static final SaveUserRepository repo = new SaveUserRepository();
	private static final CreateUserUS port = new CreateUserUS(repo);

	@PostMapping
	public String setUserAdmin(@RequestParam String nome , @RequestParam String senha) {
		UsuarioAdmin user = new UsuarioAdmin();
		user.setEmail(nome);
		user.setSenha(senha);
		var id = port.apply(user);
		return id;
	}

	public static void main(String[] args) {
		SpringApplication.run(ApiPetsApplication.class, args);
		System.out.println("||||| API RUNNING |||||");
	}

}
