import java.time.LocalDate;
import java.util.List;

public class Pedido {
    private Cliente cliente;
    private LocalDate dataCriacao;
    private List<ItemPedido> listaPedidos;
    private double valorTotal;
    private String statusPagamento;

    public Pedido() {
    }

    public Pedido(Cliente cliente, LocalDate dataCriacao, List<ItemPedido> listaPedidos, double valorTotal, String statusPagamento) {
        this.cliente = cliente;
        this.dataCriacao = dataCriacao;
        this.listaPedidos = listaPedidos;
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

    public List<ItemPedido> getListaPedidos() {
        return listaPedidos;
    }

    public void setListaPedidos(List<ItemPedido> listaPedidos) {
        this.listaPedidos = listaPedidos;
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
