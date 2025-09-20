package br.com.adatech.ecommerce.model;

public class ItemPedido {
    private Produto produto;
    private int quantidade;
    private double precoVenda;

    public ItemPedido(Produto produto, int quantidade, double precoVenda) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoVenda = precoVenda;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public double getSubtotal() {
        return this.quantidade * this.precoVenda;
    }

    @Override
    public String toString() {
        return produto.getNome() + " | Qtd: " + quantidade + " | Preço Un.: R$" + precoVenda + " | Subtotal: R$" + getSubtotal();
    }
}
