import java.util.ArrayList;
import java.util.List;

public class ProdutoRepository {
    private List<Produto> produtos = new ArrayList<>();

    public void salvar(Produto produto) {
        // Futuramente, poderia adicionar lógica para não duplicar clientes
        this.produtos.add(produto);
    }

    public List<Cliente> buscarTodos() {
        return this.produtos;
    }

    public Cliente buscarPorIndice(int index) {
        if (index >= 0 && index < produtos.size()) {
            return produtos.get(index);
        }
        return null; // Ou lançar uma exceção
    }
}
