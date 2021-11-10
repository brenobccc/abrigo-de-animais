package br.ifce.edu.lp2.core.domain;

public class Teste {
    public static void main(String[] args) {
        Animal pet = new Animal("11", "Breno", 85.00,1,"testando");

        System.out.println("Status: "+ pet.getStatus_animal());

    }
}
