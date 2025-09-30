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
        System.out.println("Clientes disponíveis:");
        clienteService.listar();

        if (clienteRepository.buscarTodos().isEmpty()) {
            System.out.println("Não há clientes cadastrados para iniciar uma venda.");
            return null;
        }

        System.out.print("\nDigite o RG do cliente para o pedido: ");
        String rgCliente = scanner.nextLine();

        Cliente clienteSelecionado = clienteRepository.buscarPorRg(rgCliente);

        if (clienteSelecionado == null) {
            System.out.println("Erro: Nenhum cliente encontrado com o RG informado.");
            return null;
        }

        Pedido novoPedido = new Pedido(clienteSelecionado);
        pedidoRepository.salvar(novoPedido);

        System.out.println("Pedido criado para o cliente: " + clienteSelecionado.nome());
        System.out.println("Status do Pedido: " + novoPedido.getStatus());

        return novoPedido;
    }

    public void adicionarItem(Pedido pedido, Scanner scanner, ProdutoService produtoService) {
        if (pedido.getStatus() != StatusPedido.ABERTO) {
            System.out.println("Este pedido não está aberto e não pode receber novos itens.");
            return;
        }

        System.out.println("\n--- Adicionar Item ao Pedido ---");
        System.out.println("Produtos disponíveis:");
        produtoService.listar();

        if (produtoRepository.buscarTodos().isEmpty()) {
            return;
        }

        try {
            System.out.print("\nDigite o nome do produto que deseja adicionar: ");
            String nomeProduto = scanner.nextLine();

            Produto produtoSelecionado = produtoRepository.buscarPorNome(nomeProduto);

            if (produtoSelecionado == null) {
                System.out.println("Erro: Produto não encontrado.");
                return;
            }

            System.out.print("Digite a quantidade: ");
            int quantidade = scanner.nextInt();
            scanner.nextLine();

            if (quantidade <= 0) {
                System.out.println("Erro: A quantidade deve ser maior que zero.");
                return;
            }

            System.out.print("Digite o preço de venda (pressione ENTER para usar o preço padrão de R$" + produtoSelecionado.precoInicial() + "): ");
            String precoVendaInput = scanner.nextLine();
            double precoVenda = produtoSelecionado.precoInicial();
            if (!precoVendaInput.isEmpty()) {
                precoVenda = Double.parseDouble(precoVendaInput);
            }

            ItemPedido novoItem = new ItemPedido(produtoSelecionado, quantidade, precoVenda);
            pedido.adicionarItem(novoItem);

            System.out.println("Item adicionado com sucesso: " + novoItem);

        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao adicionar o item. Verifique os dados digitados.");
            scanner.nextLine();
        }
    }

    public void removerItem(Pedido pedido, Scanner scanner) {
        if (pedido.getStatus() != StatusPedido.ABERTO) {
            return;
        }

        System.out.println("\n--- Remover Item do Pedido ---");
        if (pedido.getItens().isEmpty()) {
            return;
        }

        System.out.println("Itens atuais no pedido:");
        pedido.getItens().forEach(System.out::println);

        System.out.print("\nDigite o nome do produto que deseja remover do pedido: ");
        String nomeProdutoParaRemover = scanner.nextLine();

        boolean foiRemovido = pedido.getItens().removeIf(
                item -> item.produto().nome().equalsIgnoreCase(nomeProdutoParaRemover)
        );

        if (foiRemovido) {
            System.out.println("Item '" + nomeProdutoParaRemover + "' removido com sucesso!");
        } else {
            System.out.println("Erro: Item não encontrado no pedido.");
        }
    }

    public void alterarQuantidadeItem(Pedido pedido, Scanner scanner) {
        if (pedido.getStatus() != StatusPedido.ABERTO) {
            return;
        }
        System.out.println("\n--- Alterar Quantidade do Item ---");
        if (pedido.getItens().isEmpty()) {
            return;
        }

        System.out.println("Itens atuais no pedido:");
        pedido.getItens().forEach(System.out::println);

        System.out.print("\nDigite o nome do produto que deseja alterar a quantidade: ");
        String nomeProduto = scanner.nextLine();

        ItemPedido itemAntigo = pedido.getItens().stream()
                .filter(item -> item.produto().nome().equalsIgnoreCase(nomeProduto))
                .findFirst()
                .orElse(null);

        if (itemAntigo == null) {
            System.out.println("Erro: Item não encontrado no pedido.");
            return;
        }

        try {
            System.out.print("Digite a nova quantidade para '" + itemAntigo.produto().nome() + "': ");
            int novaQuantidade = scanner.nextInt();
            scanner.nextLine();

            if (novaQuantidade > 0) {
                ItemPedido itemAtualizado = new ItemPedido(itemAntigo.produto(), novaQuantidade, itemAntigo.precoVenda());

                pedido.atualizarItem(itemAtualizado);
                System.out.println("Quantidade alterada com sucesso!");
            } else {
                System.out.println("Erro: A quantidade deve ser maior que zero.");
            }
        } catch (Exception e) {
            System.out.println("Ocorreu um erro. Por favor, digite um número válido.");
            scanner.nextLine();
        }
    }

    public boolean finalizarPedido(Pedido pedido) {
        System.out.println("\n--- Finalizando Pedido ---");

        if (pedido.getItens().isEmpty()) {
            System.out.println("Erro: Não é possível finalizar um pedido sem itens.");
            return false;
        }
        if (pedido.getValorTotal() <= 0) {
            System.out.println("Erro: Não é possível finalizar um pedido com valor total igual ou menor que zero.");
            return false;
        }

        pedido.finalizar();

        System.out.println("Status do Pedido alterado para: " + pedido.getStatus());
        System.out.println("Notificação enviada para o cliente " + pedido.getCliente().nome() + " por e-mail (" + pedido.getCliente().email() + "):");
        System.out.println(">> Olá, " + pedido.getCliente().nome() + "! Seu pedido de R$" + pedido.getValorTotal() + " foi recebido e está aguardando pagamento.");

        return true;
    }

    public void pagarPedido(Pedido pedido) {
        if (pedido.getStatus() != StatusPedido.AGUARDANDO_PAGAMENTO) {
            System.out.println("Erro: Este pedido não pode ser pago, pois seu status é '" + pedido.getStatus() + "'.");
            return;
        }

        pedido.pagar();
        System.out.println("\nPagamento processado com sucesso!");
        System.out.println("Status do Pedido alterado para: " + pedido.getStatus());
        System.out.println("Notificação enviada para o cliente " + pedido.getCliente().nome() + " por e-mail (" + pedido.getCliente().email() + "):");
        System.out.println(">> Olá, " + pedido.getCliente().nome() + "! O pagamento do seu pedido foi confirmado. Em breve, ele será enviado para entrega.");
    }

    public void entregarPedido(Pedido pedido) {
        if (pedido.getStatus() != StatusPedido.PAGO) {
            System.out.println("Erro: Este pedido não pode ser entregue, pois seu status é '" + pedido.getStatus() + "'.");
            return;
        }

        pedido.entregar();
        System.out.println("\nPedido enviado para entrega!");
        System.out.println("Status do Pedido alterado para: " + pedido.getStatus());
        System.out.println("Notificação enviada para o cliente " + pedido.getCliente().nome() + " por e-mail (" + pedido.getCliente().email() + "):");
        System.out.println(">> Olá, " + pedido.getCliente().nome() + "! Seu pedido saiu para entrega. Obrigado por comprar na Ada Commerce!");
    }
}
