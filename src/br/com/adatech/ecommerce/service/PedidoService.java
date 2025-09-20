package br.com.adatech.ecommerce.service;

import br.com.adatech.ecommerce.model.Cliente;
import br.com.adatech.ecommerce.model.Pedido;
import br.com.adatech.ecommerce.repository.ClienteRepository;
import br.com.adatech.ecommerce.repository.PedidoRepository;
import br.com.adatech.ecommerce.repository.ProdutoRepository;

import java.util.Scanner;

public class PedidoService {

    private PedidoRepository pedidoRepository;
    private ClienteRepository clienteRepository;
    private ProdutoRepository produtoRepository;

    public PedidoService(PedidoRepository pedidoRepository, ClienteRepository clienteRepository, ProdutoRepository produtoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
    }

    public Pedido iniciarVenda(ClienteService clienteService, Scanner scanner) {
        System.out.println("\n--- Iniciando Nova Venda ---");
        System.out.println("Selecione o cliente:");
        clienteService.listar();

        if (clienteRepository.buscarTodos().isEmpty()) {
            return null;
        }

        System.out.print("Digite o índice do cliente: ");
        int indiceCliente = scanner.nextInt();
        scanner.nextLine();

        Cliente clienteSelecionado = clienteRepository.buscarPorIndice(indiceCliente);

        if (clienteSelecionado == null) {
            System.out.println("Erro: Cliente inválido.");
            return null;
        }

        Pedido novoPedido = new Pedido(clienteSelecionado);
        pedidoRepository.salvar(novoPedido);

        System.out.println("Pedido criado para o cliente: " + clienteSelecionado.getNome());
        System.out.println("Status do Pedido: " + novoPedido.getStatus());

        return novoPedido;
    }
}
