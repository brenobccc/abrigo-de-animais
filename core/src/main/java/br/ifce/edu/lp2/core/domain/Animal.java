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
    private String tipo_animal;
    private String raca_animal;

    public Animal(){

    }

    public Status verificarStatus(int value) {
        switch(value){
            case 1: return Status.INDISPONIVEL;
            case 2: return Status.DISPONIVEL;
            case 3: return Status.ADOTADO;
            default: return null;
        }
    }

}
