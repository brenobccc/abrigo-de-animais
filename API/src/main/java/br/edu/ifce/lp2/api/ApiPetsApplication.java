package br.edu.ifce.lp2.api;

import br.ifce.edu.lp2.core.domain.Usuario;
import br.ifce.edu.lp2.core.ports.driver.CreateUserPort;
import br.ifce.edu.lp2.core.us.CreateUserUS;
import mongodb.adapters.SaveUserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController("/")
public class ApiPetsApplication {


	/*@GetMapping(value = "nome")
	public static String createUser(){



	}*/

	public static void main(String[] args) {


		SpringApplication.run(ApiPetsApplication.class, args);
		System.out.println("teste");

		var user = new Usuario();
		user.setNome("cascona test s2");
		user.setTelefone("(88)940405080");


		var repo = new SaveUserRepository();
		var port = new CreateUserUS(repo);
		port.apply(user);

		repo.apply(user);



		System.out.println("nome: " + user.getNome());

	}

}
