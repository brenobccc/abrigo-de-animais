package br.ifce.edu.lp2.core.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TipoAnimal {
    private String id;
    private String descricao;

    public TipoAnimal(String id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }
}
