package br.ifce.edu.lp2.core.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Usuario {
    private String id;
    private String telefone;
    private String nome;
    private List<Animal> Animais;//minimo 0 no máximo N


}
