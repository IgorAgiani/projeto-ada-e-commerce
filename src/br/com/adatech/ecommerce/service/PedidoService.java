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
        produtoService.listar();

        if (produtoRepository.buscarTodos().isEmpty()) {
            return;
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

            System.out.print("Digite o preço de venda (pressione ENTER para usar o preço padrão de R$" + produtoSelecionado.getPrecoInicial() + "): ");
            String precoVendaInput = scanner.nextLine();

            double precoVenda = produtoSelecionado.getPrecoInicial();
            if (!precoVendaInput.isEmpty()) {
                precoVenda = Double.parseDouble(precoVendaInput);
            }

            ItemPedido novoItem = new ItemPedido(produtoSelecionado, quantidade, precoVenda);
            pedido.getItens().add(novoItem);

            System.out.println("Item adicionado com sucesso: " + novoItem);

        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao adicionar o item. Verifique os dados digitados.");
            scanner.nextLine(); // Limpa o buffer em caso de erro
        }
    }

    public void removerItem(Pedido pedido, Scanner scanner) {
        if (pedido.getStatus() != StatusPedido.ABERTO) {
            System.out.println("Este pedido não está aberto e não pode ser modificado.");
            return;
        }

        System.out.println("\n--- Remover Item do Pedido ---");
        if (pedido.getItens().isEmpty()) {
            System.out.println("O pedido não contém itens para remover.");
            return;
        }

        // Mostra os itens atuais com seus índices
        System.out.println("Itens atuais no pedido:");
        for (int i = 0; i < pedido.getItens().size(); i++) {
            System.out.println(i + " - " + pedido.getItens().get(i));
        }

        try {
            System.out.print("Digite o índice do item que deseja remover: ");
            int indice = scanner.nextInt();
            scanner.nextLine();

            if (indice >= 0 && indice < pedido.getItens().size()) {
                ItemPedido itemRemovido = pedido.getItens().remove(indice);
                System.out.println("Item removido com sucesso: " + itemRemovido.getProduto().getNome());
            } else {
                System.out.println("Erro: Índice inválido.");
            }
        } catch (Exception e) {
            System.out.println("Ocorreu um erro. Por favor, digite um número válido.");
            scanner.nextLine();
        }
    }

    public void alterarQuantidadeItem(Pedido pedido, Scanner scanner) {
        if (pedido.getStatus() != StatusPedido.ABERTO) {
            System.out.println("Este pedido não está aberto e não pode ser modificado.");
            return;
        }

        System.out.println("\n--- Alterar Quantidade do Item ---");
        if (pedido.getItens().isEmpty()) {
            System.out.println("O pedido não contém itens para alterar.");
            return;
        }

        System.out.println("Itens atuais no pedido:");
        for (int i = 0; i < pedido.getItens().size(); i++) {
            System.out.println(i + " - " + pedido.getItens().get(i));
        }

        try {
            System.out.print("Digite o índice do item que deseja alterar: ");
            int indice = scanner.nextInt();
            scanner.nextLine();

            if (indice >= 0 && indice < pedido.getItens().size()) {
                ItemPedido itemParaAlterar = pedido.getItens().get(indice);

                System.out.print("Digite a nova quantidade para '" + itemParaAlterar.getProduto().getNome() + "': ");
                int novaQuantidade = scanner.nextInt();
                scanner.nextLine();

                if (novaQuantidade > 0) {
                    itemParaAlterar.setQuantidade(novaQuantidade);
                    System.out.println("Quantidade alterada com sucesso!");
                } else {
                    System.out.println("Erro: A quantidade deve ser maior que zero. Para remover o item, use a opção 3.");
                }
            } else {
                System.out.println("Erro: Índice inválido.");
            }
        } catch (Exception e) {
            System.out.println("Ocorreu um erro. Por favor, digite um número válido.");
            scanner.nextLine();
        }
    }
}
