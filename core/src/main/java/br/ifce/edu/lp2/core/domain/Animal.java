package br.ifce.edu.lp2.core.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Animal {
    private String id;
    private String nome;
    private Double peso;
    private Status status_animal;
    private TipoAnimal tipo_animal;
    private RacaAnimal raca_animal;


    Animal(String id, String nome, Double peso, int estado_do_animal, String descricao){
        this.id = id;
        this.nome = nome;
        this.peso = peso;
        this.status_animal = verificarStatus(estado_do_animal);
        this.tipo_animal = new TipoAnimal(id, descricao);
        this.raca_animal = new RacaAnimal(id,descricao);
    }

    private Status verificarStatus(int value) {
        switch(value){
            case 1: return Status.INDISPONIVEL;
            case 2: return Status.DISPONIVEL;
            case 3: return Status.ADOTADO;
            default: return null;
        }
    }

}
