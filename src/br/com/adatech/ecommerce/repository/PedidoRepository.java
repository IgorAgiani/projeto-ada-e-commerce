package br.com.adatech.ecommerce.repository;

import br.com.adatech.ecommerce.model.Pedido;

import java.util.ArrayList;
import java.util.List;

public class PedidoRepository {
    private List<Pedido> pedidos = new ArrayList<>();

    public void salvar(Pedido pedido) {
        this.pedidos.add(pedido);
    }

    public List<Pedido> buscarTodos() {
        return new ArrayList<>(pedidos);
    }
}
