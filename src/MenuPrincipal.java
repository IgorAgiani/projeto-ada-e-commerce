import java.util.Scanner;

public class MenuPrincipal {
    private Scanner scanner = new Scanner(System.in);
    private Produto produto = new Produto();
    private Cliente cliente = new Cliente();

    Cliente igor = new Cliente("Igor", "12.456.789-00", "igor@email.com");
    Cliente maria = new Cliente("Maria", "98.654.321-00", "maria@email.com");

    Produto notebook = new Produto("Notebook", 2500.00, 50);
    Produto smartphone = new Produto("Smartphone", 1500.00, 50);
    Produto tablet = new Produto("Tablet", 800.00, 50);


    public void iniciar() {

        cliente.salvar(igor);
        cliente.salvar(maria);

        produto.salvar(notebook);
        produto.salvar(smartphone);
        produto.salvar(tablet);

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
                cliente.cadastrar(scanner);
                break;
            case 2:
                cliente.listar();
                break;
            case 3:
                cliente.atualizar();
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
                    produto.cadastrar(scanner);
                    break;
                case 2:
                    produto.listar();
                    break;
                case 3:
                    produto.atualizar();
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