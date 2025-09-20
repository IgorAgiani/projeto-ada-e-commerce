package br.com.adatech.ecommerce.service;

import br.com.adatech.ecommerce.model.*;
import br.com.adatech.ecommerce.repository.ClienteRepository;
import br.com.adatech.ecommerce.repository.PedidoRepository;
import br.com.adatech.ecommerce.repository.ProdutoRepository;
import br.com.adatech.ecommerce.model.StatusPedido;

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

    public void adicionarItem(Pedido pedido, Scanner scanner, ProdutoService produtoService) {
        if (pedido.getStatus() != StatusPedido.ABERTO) {
            System.out.println("Este pedido não está aberto e não pode receber novos itens.");
            return;
        }

        System.out.println("\n--- Adicionar Item ao Pedido ---");
        System.out.println("Selecione um produto da lista:");
        produtoService.listar(); // Reutiliza o serviço de produto para mostrar a lista

        if (produtoRepository.buscarTodos().isEmpty()) {
            return; // Sai se não há produtos para adicionar
        }

        try {
            System.out.print("Digite o índice do produto: ");
            int indiceProduto = scanner.nextInt();
            scanner.nextLine();

            Produto produtoSelecionado = produtoRepository.buscarPorIndice(indiceProduto);

            if (produtoSelecionado == null) {
                System.out.println("Erro: Produto inválido.");
                return;
            }

            System.out.print("Digite a quantidade: ");
            int quantidade = scanner.nextInt();
            scanner.nextLine();

            if (quantidade <= 0) {
                System.out.println("Erro: A quantidade deve ser maior que zero.");
                return;
            }

            // Regra: O valor de venda pode ser diferente do valor do produto.
            System.out.print("Digite o preço de venda (pressione ENTER para usar o preço padrão de R$" + produtoSelecionado.getPrecoInicial() + "): ");
            String precoVendaInput = scanner.nextLine();

            double precoVenda = produtoSelecionado.getPrecoInicial(); // Usa o preço padrão se nada for digitado
            if (!precoVendaInput.isEmpty()) {
                precoVenda = Double.parseDouble(precoVendaInput);
            }

            // Cria o ItemPedido e o adiciona à lista de itens do pedido
            ItemPedido novoItem = new ItemPedido(produtoSelecionado, quantidade, precoVenda);
            pedido.getItens().add(novoItem);

            System.out.println("Item adicionado com sucesso: " + novoItem);

        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao adicionar o item. Verifique os dados digitados.");
            scanner.nextLine(); // Limpa o buffer em caso de erro
        }
    }
}
