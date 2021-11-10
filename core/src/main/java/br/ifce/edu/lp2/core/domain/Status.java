package br.ifce.edu.lp2.core.domain;

public enum Status {
    //opcoes de escolha
    INDISPONIVEL(1), DISPONIVEL(2), ADOTADO(3);

    private final int valor;

    Status(int valorOpcao){
        valor = valorOpcao;
    }

    public int getValor(){
        return valor;
    }
}
