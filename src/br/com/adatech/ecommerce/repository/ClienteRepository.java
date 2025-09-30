package br.com.adatech.ecommerce.repository;

import br.com.adatech.ecommerce.model.Cliente;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClienteRepository {

    private Map<String, Cliente> clientes = new HashMap<>();

    public void salvar(Cliente cliente) {
        this.clientes.put(cliente.rg(), cliente);
    }

    public List<Cliente> buscarTodos() {
        return new ArrayList<>(this.clientes.values());
    }

    public Cliente buscarPorRg(String rg) {
        return this.clientes.get(rg);
    }
}
