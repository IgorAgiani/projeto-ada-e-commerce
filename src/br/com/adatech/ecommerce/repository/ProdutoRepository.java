package br.com.adatech.ecommerce.repository;

import br.com.adatech.ecommerce.model.Produto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProdutoRepository {

    private Map<String, Produto> produtos = new HashMap<>();

    public void salvar(Produto produto) {
        this.produtos.put(produto.nome(), produto);
    }

    public List<Produto> buscarTodos() {
        return new ArrayList<>(this.produtos.values());
    }

    public Produto buscarPorNome(String nome) {
        return this.produtos.get(nome);
    }

    public void remover(String nome) {
        this.produtos.remove(nome);
    }
}