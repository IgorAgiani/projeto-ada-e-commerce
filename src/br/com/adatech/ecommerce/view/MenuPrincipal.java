package br.com.adatech.ecommerce.view;

import br.com.adatech.ecommerce.model.Cliente;
import br.com.adatech.ecommerce.model.Pedido;
import br.com.adatech.ecommerce.model.Produto;
import br.com.adatech.ecommerce.model.StatusPedido;
import br.com.adatech.ecommerce.repository.ClienteRepository;
import br.com.adatech.ecommerce.repository.PedidoRepository;
import br.com.adatech.ecommerce.repository.ProdutoRepository;
import br.com.adatech.ecommerce.service.ClienteService;
import br.com.adatech.ecommerce.service.PedidoService;
import br.com.adatech.ecommerce.service.ProdutoService;

import java.util.List;
import java.util.Scanner;

public class MenuPrincipal {
    private Scanner scanner = new Scanner(System.in);

    private ClienteRepository clienteRepository = new ClienteRepository();
    private ProdutoRepository produtoRepository = new ProdutoRepository();
    private PedidoRepository pedidoRepository = new PedidoRepository();

    private ClienteService clienteService = new ClienteService(clienteRepository);
    private ProdutoService produtoService = new ProdutoService(produtoRepository);
    private PedidoService pedidoService = new PedidoService(pedidoRepository, clienteRepository, produtoRepository);

    public void iniciar() {

        clienteRepository.salvar(new Cliente("Igor", "12.456.789-00", "igor@email.com"));
        clienteRepository.salvar(new Cliente("Maria", "98.654.321-00", "maria@email.com"));

        produtoRepository.salvar(new Produto("Notebook", 2500.00, 50));
        produtoRepository.salvar(new Produto("Smartphone", 1500.00, 50));
        produtoRepository.salvar(new Produto("Tablet", 800.00, 50));


        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- ADA COMMERCE ---");
            System.out.println("1 - Gerenciar Clientes");
            System.out.println("2 - Gerenciar Produtos");
            System.out.println("3 - Criar Nova Venda");
            System.out.println("4 - Processar Pagamento/Entrega de Pedidos");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> menuClientes();
                case 2 -> menuProdutosEstoque();
                case 3 -> menuVenda();
                case 4 -> menuProcessarPedidos();
                case 0 -> System.out.println("Encerrando o sistema...");
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private void menuClientes() {
        System.out.println("\n--- CLIENTES ---");
        System.out.println("1 - Cadastrar");
        System.out.println("2 - Listar Clientes");
        System.out.println("3 - Atualizar");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1 -> clienteService.cadastrar(scanner);
            case 2 -> clienteService.listar();
            case 3 -> clienteService.atualizar(scanner);
            default -> System.out.println("Opção inválida.");
        }
    }

    private void menuProdutosEstoque() {
        System.out.println("\n--- PRODUTOS EM ESTOQUE ---");
        System.out.println("1 - Cadastrar");
        System.out.println("2 - Listar Produtos");
        System.out.println("3 - Atualizar");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer

        switch (opcao) {
            case 1 -> produtoService.cadastrar(scanner);
            case 2 -> produtoService.listar();
            case 3 -> produtoService.atualizar(scanner);
            default -> System.out.println("Opção inválida.");
        }
    }

    private void menuVenda() {
        Pedido pedidoAtual = pedidoService.iniciarVenda(clienteService, scanner);
        if (pedidoAtual != null) {
            gerenciarItensDoPedido(pedidoAtual);
        }
    }


    private void gerenciarItensDoPedido (Pedido pedido){
        int opcao = -1;
        while (opcao != 0 && pedido.getStatus() == StatusPedido.ABERTO) {
            System.out.println("\n--- Gerenciando Pedido de " + pedido.getCliente().nome() + " ---");
            System.out.println("Status: " + pedido.getStatus() + " | Itens: " + pedido.getItens().size() + " | Valor Total: R$" + pedido.getValorTotal());
            System.out.println("1 - Adicionar Item");
            System.out.println("2 - Alterar Quantidade de um Item");
            System.out.println("3 - Remover Item");
            System.out.println("4 - Finalizar Pedido");
            System.out.println("0 - Cancelar Pedido");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> pedidoService.adicionarItem(pedido, scanner, produtoService);
                case 2 -> pedidoService.alterarQuantidadeItem(pedido, scanner);
                case 3 -> pedidoService.removerItem(pedido, scanner);
                case 4 -> {
                    pedidoService.finalizarPedido(pedido);
                }
                case 0 -> System.out.println("Pedido cancelado.");
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private void menuProcessarPedidos() {
        System.out.println("\n--- Processar Pedidos Pendentes ---");
        List<Pedido> todosOsPedidos = pedidoRepository.buscarTodos();

        if (todosOsPedidos.isEmpty()) {
            System.out.println("Não há nenhum pedido na base.");
            return;
        }

        for (int i = 0; i < todosOsPedidos.size(); i++) {
            Pedido p = todosOsPedidos.get(i);
            System.out.println(i + " - Cliente: " + p.getCliente().nome() + " | Status: " + p.getStatus() + " | Valor: R$" + p.getValorTotal());
        }

        System.out.print("\nEscolha o índice do pedido que deseja processar: ");
        int indice = scanner.nextInt();
        scanner.nextLine();

        if (indice < 0 || indice >= todosOsPedidos.size()) {
            System.out.println("Índice inválido.");
            return;
        }

        Pedido pedidoSelecionado = todosOsPedidos.get(indice);

        if (pedidoSelecionado.getStatus() == StatusPedido.AGUARDANDO_PAGAMENTO) {
            System.out.print("Processar o PAGAMENTO deste pedido? (s/n): ");
            String resposta = scanner.nextLine();
            if (resposta.equalsIgnoreCase("s")) {
                pedidoService.pagarPedido(pedidoSelecionado);
            }
        } else if (pedidoSelecionado.getStatus() == StatusPedido.PAGO) {
            System.out.print("Processar a ENTREGA deste pedido? (s/n): ");
            String resposta = scanner.nextLine();
            if (resposta.equalsIgnoreCase("s")) {
                pedidoService.entregarPedido(pedidoSelecionado);
            }
        } else {
            System.out.println("O pedido selecionado não pode ser processado no momento (Status: " + pedidoSelecionado.getStatus() + ").");
        }
    }
}