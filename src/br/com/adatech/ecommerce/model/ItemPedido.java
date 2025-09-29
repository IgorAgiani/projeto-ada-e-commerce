package br.com.adatech.ecommerce.model;

public record ItemPedido(Produto produto, int quantidade, double precoVenda) {

    public double getSubtotal() {
        return this.quantidade * this.precoVenda;
    }

    @Override
    public String toString() {
        return produto.getNome() + " | Qtd: " + quantidade + " | Preço Un.: R$" + precoVenda + " | Subtotal: R$" + getSubtotal();
    }
}