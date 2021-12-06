package br.edu.ifce.lp2.api;

import br.edu.ifce.lp2.api.controllers.AnimalController;
import br.edu.ifce.lp2.api.controllers.UserAdminController;
import br.edu.ifce.lp2.api.controllers.UserController;
import br.ifce.edu.lp2.core.domain.Animal;
import br.ifce.edu.lp2.core.domain.UsuarioAdmin;
import br.ifce.edu.lp2.core.us.CreateUserAdminUS;
import mongodb.adapters.SaveUserAdminRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
public class ApiPetsApplication {
	//Funcao principal para TESTES
	public static void main(String[] args) {
		SpringApplication.run(ApiPetsApplication.class, args);

		//AnimalController animalController = new AnimalController();
		UserAdminController useAdm = new UserAdminController();
		UserController user = new UserController();
		AnimalController animal = new AnimalController();
		//UserController userController = new UserController();

		System.out.println("||||| API RUNNING |||||");
	}

}
