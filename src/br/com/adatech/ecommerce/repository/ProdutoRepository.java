package br.com.adatech.ecommerce.repository;

import br.com.adatech.ecommerce.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoRepository {
    private List<Produto> produtos = new ArrayList<>();

    public void salvar(Produto produto) {
        this.produtos.add(produto);
    }

    public List<Produto> buscarTodos() {
        return this.produtos;
    }

    public Produto buscarPorIndice(int index) {
        if (index >= 0 && index < produtos.size()) {
            return produtos.get(index);
        }
        return null;
    }

    public void atualizar(int index, Produto produtoAtualizado) {
        if (index >= 0 && index < this.produtos.size()) { // 'produtos' seria sua lista interna
            this.produtos.set(index, produtoAtualizado);
        }
    }

}
