package br.com.adatech.ecommerce.model;

import java.util.ArrayList;
import java.util.List;

public class ItemPedido {
    private Produto produto;
    private int quantidade;
    private double precoVenda;
    private List<ItemPedido> listaPedidos = new ArrayList<>();

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

    public List<ItemPedido> getListaPedidos() {
        return listaPedidos;
    }

    public void setListaPedidos(List<ItemPedido> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }
}
