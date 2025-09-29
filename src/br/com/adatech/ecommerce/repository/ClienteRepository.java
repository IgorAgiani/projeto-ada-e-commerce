package br.com.adatech.ecommerce.repository;

import br.com.adatech.ecommerce.model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteRepository {

    private List<Cliente> clientes = new ArrayList<>();

    public void salvar(Cliente cliente) {
        this.clientes.add(cliente);
    }

    public List<Cliente> buscarTodos() {
        return this.clientes;
    }

    public Cliente buscarPorIndice(int index) {
        if (index >= 0 && index < clientes.size()) {
            return clientes.get(index);
        }
        return null;
    }

    public void atualizar(int index, Cliente clienteAtualizado) {
        if (index >= 0 && index < clientes.size()) {
            this.clientes.set(index, clienteAtualizado);
        }
    }

}
