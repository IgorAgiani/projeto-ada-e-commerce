import java.time.LocalDate;
import java.util.List;

public class Pedido {
    private Cliente cliente;
    private LocalDate dataCriacao;
    private List<ItemPedido> itens;
    private double valorTotal;
    private String statusPagamento;

    public Pedido(Cliente cliente, LocalDate dataCriacao, List<ItemPedido> itens, double valorTotal, String statusPagamento) {
        this.cliente = cliente;
        this.dataCriacao = dataCriacao;

        this.itens = itens;
        this.valorTotal = valorTotal;
        this.statusPagamento = statusPagamento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(String statusPagamento) {
        this.statusPagamento = statusPagamento;
    }
}
