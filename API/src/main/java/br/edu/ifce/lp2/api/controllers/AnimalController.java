package br.edu.ifce.lp2.api.controllers;

import br.ifce.edu.lp2.core.domain.Animal;
import br.ifce.edu.lp2.core.domain.RacaAnimal;
import br.ifce.edu.lp2.core.domain.TipoAnimal;
import br.ifce.edu.lp2.core.domain.UsuarioAdmin;
import br.ifce.edu.lp2.core.us.CreateAnimalUS;
import br.ifce.edu.lp2.core.us.CreateUserAdminUS;
import mongodb.adapters.SaveAnimalRepository;
import mongodb.adapters.SaveUserAdminRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/animais/")
public class AnimalController {
    private static final SaveAnimalRepository repo = new SaveAnimalRepository();
    private static final CreateAnimalUS port = new CreateAnimalUS(repo);

    @PostMapping//Criando um usuário(passo o nome e senha)
    public String doarAnimal(@RequestParam String nome, @RequestParam Double peso, @RequestParam String tipo_animal, @RequestParam String raca_animal, @RequestParam int status_animal) {
        Animal animal = new Animal();//Cria um usuário

        animal.setPeso(peso);
        animal.setNome(nome);
        animal.setTipo_animal(tipo_animal);
        animal.setRaca_animal(raca_animal);
        animal.setStatus_animal(animal.verificarStatus(status_animal));

        var id = port.apply(animal);
        return id;
        //var id = port.apply(user);//mando o meu usuário pro repositório e pego o ID

       // return id;//retorna o ID do usuário cadaastrado
    }

    @PutMapping//Deletando o usuário
    public String adotarAnimal(@RequestParam String id_animal, @RequestParam String id_usuario) {
        return port.apply(id_animal, id_usuario);//Retorna uma mensagem informando
        // se foi cadastrado com sucesso ou não.
    }


    @PutMapping(value = "devolveranimal/")
    public String devolverAnimal(@RequestParam String id_animal, @RequestParam String id_usuario) {
        return port.devolverAnimal(id_animal, id_usuario);//Retorna uma mensagem informando
        // se foi cadastrado com sucesso ou não.
    }



    @GetMapping(value = "animal/")//Deletando o usuário
    public Animal getAnimal(@RequestParam String id_animal) {
        return port.getAnimal(id_animal);//Retorna uma mensagem informando
    }

    @GetMapping//Deletando o usuário
    public List<Animal> getAll() {
        return port.getAll();//Retorna uma mensagem informando
    }

    @DeleteMapping
    public String applyDelete(@RequestParam String id){
        return port.applyDelete(id);
    }
    /*@PutMapping//atualizando usuario
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
    }*/
}
