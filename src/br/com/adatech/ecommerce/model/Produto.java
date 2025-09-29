package br.com.adatech.ecommerce.model;

public record Produto(String nome, double precoInicial, int quantidade) {

    @Override
    public String toString() {
        return nome + " - R$" + precoInicial + " - estoque: " + quantidade;
    }
}