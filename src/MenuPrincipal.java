import java.util.Scanner;

public class MenuPrincipal {
    private Scanner scanner = new Scanner(System.in);

    private ClienteRepository clienteRepository = new ClienteRepository();
    private ProdutoRepository produtoRepository = new ProdutoRepository();

    private ClienteService clienteService = new ClienteService(clienteRepository);
    private ProdutoService produtoService = new ProdutoService(produtoRepository);

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
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> menuClientes();
                case 2 -> menuProdutosEstoque();
                case 3 -> menuVenda();
                case 0 -> System.out.println("Encerrando o sistema...");
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private void menuClientes() {
        System.out.println("\n--- CLIENTES ---");
        System.out.println("1 - Cadastrar Cliente");
        System.out.println("2 - Listar Clientes");
        System.out.println("3 - Atualizar Cliente");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1 -> clienteService.cadastrarCliente(scanner);
            case 2 -> clienteService.listarClientes();
            case 3 -> clienteService.atualizarCliente(scanner);
            default -> System.out.println("Opção inválida.");
        }
    }

    private void menuProdutosEstoque() {
        System.out.println("\n--- PRODUTOS EM ESTOQUE ---");
        System.out.println("1 - Cadastrar Produto");
        System.out.println("2 - Listar Produtos");
        System.out.println("3 - Atualizar Produto");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer

        switch (opcao) {
            case 1 -> produtoService.cadastrarProduto(scanner);
            case 2 -> produtoService.listarProdutos();
            case 3 -> produtoService.atualizarProduto(scanner);
            default -> System.out.println("Opção inválida.");
        }
    }

    private void menuVenda() {
        System.out.println("\nFuncionalidade de venda a ser implementada...");
    }
}