import java.util.ArrayList;
import java.util.List;

public class ItemPedido {
    private Produto produto;
    private int quantidade;
    private List<ItemPedido> listaPedidos = new ArrayList<>();

    public ItemPedido() {
    }

    public ItemPedido(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
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

    public void adicionarItem(Produto produto, int quantidade) {
        listaPedidos.add(new ItemPedido(produto, quantidade));
    }

    public void removerItem(Produto produtoEscolhido) {
        // implementar remoção
    }

    public void alterarQuantidade(Produto produto, int novaQuantidade) {
        // implementar alteração
    }
}
