package br.ifce.edu.lp2.core.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RacaAnimal {
    private String id;
    private String descricao;

    public RacaAnimal(String id, String descricao) {
    }
}

