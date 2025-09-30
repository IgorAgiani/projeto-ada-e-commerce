package br.com.adatech.ecommerce.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pedido {
    private final Cliente cliente;
    private final LocalDate dataCriacao;
    private StatusPedido status;
    private List<ItemPedido> itens;

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
        this.dataCriacao = LocalDate.now();
        this.status = StatusPedido.ABERTO;
        this.itens = new ArrayList<>();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public List<ItemPedido> getItens() {
        return Collections.unmodifiableList(itens);
    }

    public void adicionarItem(ItemPedido novoItem) {
        for (int i = 0; i < this.itens.size(); i++) {
            ItemPedido itemExistente = this.itens.get(i);
            if (itemExistente.produto().equals(novoItem.produto())) {
                int novaQuantidade = itemExistente.quantidade() + novoItem.quantidade();
                ItemPedido itemAtualizado = new ItemPedido(itemExistente.produto(), novaQuantidade, itemExistente.precoVenda());
                this.itens.set(i, itemAtualizado);
                return;
            }
        }
        this.itens.add(novoItem);
    }

    public double getValorTotal() {
        return this.itens.stream()
                .mapToDouble(item -> item.quantidade() * item.precoVenda())
                .sum();
    }

    public void atualizarItem(ItemPedido itemAtualizado) {
        for (int i = 0; i < this.itens.size(); i++) {
            if (this.itens.get(i).produto().equals(itemAtualizado.produto())) {
                this.itens.set(i, itemAtualizado);
                return;
            }
        }
    }

    public void finalizar() {
        if (this.status == StatusPedido.ABERTO) {
            this.setStatus(StatusPedido.AGUARDANDO_PAGAMENTO);
        }
    }

    public void pagar() {
        if (this.status == StatusPedido.AGUARDANDO_PAGAMENTO) {
            this.setStatus(StatusPedido.PAGO);
        }
    }

    public void entregar() {
        if (this.status == StatusPedido.PAGO) {
            this.setStatus(StatusPedido.FINALIZADO);
        }
    }

    void setStatus(StatusPedido status) {
        this.status = status;
    }

    public boolean removerItemPorNome(String nomeProdutoParaRemover) {
        return this.itens.removeIf(item -> item.produto().nome().equalsIgnoreCase(nomeProdutoParaRemover));
    }
}