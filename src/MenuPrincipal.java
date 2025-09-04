import java.util.Scanner;

public class MenuPrincipal {
    private Scanner scanner = new Scanner(System.in);
    private Produto produto = new Produto();
    private Cliente cliente = new Cliente();

    public void iniciar() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- ADA COMMERCE ---");
            System.out.println("1 - Gerenciar Clientes");
            System.out.println("2 - Gerenciar Produtos");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> menuClientes();
                case 2 -> menuProdutos();
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
            case 1 -> cliente.cadastrarCliente(scanner);
            case 2 -> cliente.listarClientes();
            case 3 -> cliente.atualizarClientes();
            default -> System.out.println("Opção inválida.");

        }
    }
    private void menuProdutos() {
        System.out.println("\n--- PRODUTOS ---");
        System.out.println("1 - Cadastrar");
        System.out.println("2 - Listar");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1 -> produto.cadastrarProduto(scanner);
            case 2 -> produto.listarProdutos();
            case 3 -> produto.atualizarProdutos();
            default -> System.out.println("Opção inválida.");
        }
    }
}