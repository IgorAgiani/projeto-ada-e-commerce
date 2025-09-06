import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Pedido {
    private Cliente cliente;
    private LocalDate dataCriacao;
    private double valorTotal;
    private String statusPagamento;
    private List<ItemPedido> itens;

    public Pedido() {
    }

    public Pedido(Cliente cliente, LocalDate dataCriacao, double valorTotal, String statusPagamento, List<ItemPedido> itens) {
        this.cliente = cliente;
        this.dataCriacao = dataCriacao;
        this.valorTotal = valorTotal;
        this.statusPagamento = statusPagamento;
        this.itens = itens;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    public String getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(String statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
