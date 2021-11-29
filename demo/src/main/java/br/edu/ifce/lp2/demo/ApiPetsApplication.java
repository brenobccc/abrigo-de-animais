package br.edu.ifce.lp2.demo;

import br.ifce.edu.lp2.core.domain.Usuario;
import br.ifce.edu.lp2.core.ports.driver.CreateUserPort;
import br.ifce.edu.lp2.core.us.CreateUserUS;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController("/")
public class ApiPetsApplication {


	@GetMapping(value = "nome")
	public static String createUser(){
		var user = new Usuario();
		user.setNome("Cascona s2");
		user.setTelefone("(88)940405080");
		CreateUserPort createUserPort = new CreateUserUS();//CREATE
		var id = createUserPort.apply(user);

		System.out.println("nome: " + user.getNome());
		return user.getNome();
	}

	public static void main(String[] args) {
		SpringApplication.run(ApiPetsApplication.class, args);
		System.out.println("teste");
	}

}
