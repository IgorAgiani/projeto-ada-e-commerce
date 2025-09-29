package br.com.adatech.ecommerce.model;

public record Cliente(String nome, String rg, String email) {

    @Override
    public String toString() {
        return nome + " - RG: " + rg + " - E-mail: " + email;
    }
}