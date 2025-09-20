import java.util.Scanner;

public class MenuPrincipal {
    private Scanner scanner = new Scanner(System.in);
    private ClienteRepository clienteRepository = new ClienteRepository();
    private ProdutoRepository produtoRepository = new ProdutoRepository();

    private ClienteService clienteService = new ClienteService(clienteRepository);
    private ProdutoService produtoService = new ProdutoService(produtoRepository);

    Produto notebook = new Produto("Notebook", 2500.00, 50);
    Produto smartphone = new Produto("Smartphone", 1500.00, 50);
    Produto tablet = new Produto("Tablet", 800.00, 50);


    public void iniciar() {

        clienteRepository.salvar(new Cliente("Igor", "12.456.789-00", "igor@email.com"));
        clienteRepository.salvar(new Cliente("Maria", "98.654.321-00", "maria@email.com"));

        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- ADA COMMERCE ---");
            System.out.println("1 - Gerenciar Clientes");
            System.out.println("2 - Gerenciar Produtos do Estoque");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> menuClientes();
                case 2 -> menuProdutosEstoque();
                case 0 -> System.out.println("Encerrando o sistema...");
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private void menuClientes() {

        System.out.println("\n--- CLIENTES ---");
        System.out.println("1 - Cadastrar");
        System.out.println("2 - Listar Clientes");
        System.out.println("3 - Atualizar Clientes");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                clienteService.cadastrarCliente(scanner);
                break;
            case 2:
                clienteService.listarClientes();
                break;
            case 3:
                clienteService.atualizarCliente();
                break;
            default:
                System.out.println("Opção inválida.");
                break;
        }
    }

    private void menuProdutosEstoque() {

        System.out.println("\n--- PRODUTOS EM ESTOQUE ---");
        System.out.println("1 - Cadastrar");
        System.out.println("2 - Listar");
        System.out.println("3 - Atualizar");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
                case 1:
                    produtoService.cadastrarProduto(scanner);
                    break;
                case 2:
                    produtoService.listarProdutos();
                    break;
                case 3:
                    produtoService.atualizarProduto(scanner);
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
        }
    }

    private void menuVenda() {
        // implementar
    }
}