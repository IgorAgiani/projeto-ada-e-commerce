import java.util.Scanner;

public class MenuPrincipal {
    private Scanner scanner = new Scanner(System.in);
    private Produto produto = new Produto();
    private Cliente cliente = new Cliente();
    private ItemPedido itemPedido = new ItemPedido();
    private Pedido pedido = new Pedido();

    public void iniciar() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- ADA COMMERCE ---");
            System.out.println("1 - Gerenciar Clientes");
            System.out.println("2 - Gerenciar Produtos");
            System.out.println("3 - Gerenciar Pedido");
            System.out.println("4 - Exibir Pedido");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> menuClientes();
                case 2 -> menuProdutos();
                case 3 -> menuItemPedido();
                case 4 -> exibirPedido();
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
            case 1 -> cliente.cadastrar(scanner);
            case 2 -> cliente.listar();
            case 3 -> cliente.atualizar();
            default -> System.out.println("Opção inválida.");

        }
    }

    private void menuProdutos() {
        System.out.println("\n--- PRODUTOS ---");
        System.out.println("1 - Cadastrar");
        System.out.println("2 - Listar");
        System.out.println("3 - Atualizar");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1 -> produto.cadastrar(scanner);
            case 2 -> produto.listar();
            case 3 -> produto.atualizar();
            default -> System.out.println("Opção inválida.");
        }
    }

    private void menuItemPedido() {
        System.out.println("\n--- ITENS DO PEDIDO ---");
        System.out.println("1 - Escolha o Cliente");
        System.out.println("2 - Adicionar Item");
        System.out.println("3 - Remover Item");
        System.out.println("4 - Alterar Quantidade");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1 -> {
                cliente.listar();
                System.out.print("Escolha o cliente pelo número: ");
                int numero = scanner.nextInt();
                scanner.nextLine();

                Cliente clienteEscolhido = cliente.escolherPorIndice(numero);
                pedido.setCliente(clienteEscolhido);
                System.out.println("Cliente escolhido: " + clienteEscolhido.getNome());
            }
            case 2 -> {
                produto.listar();
                System.out.print("Escolha o produto pelo número: ");
                int numero = scanner.nextInt();

                Produto produtoEscolhido = produto.escolherPorIndice(numero);

                System.out.print("Quantidade: ");
                int quantidade = scanner.nextInt();

                System.out.print("Produto: " + produtoEscolhido.getNome() + " - Quantidade: " + quantidade +
                        "\nPreço Total R$ " + produtoEscolhido.getPreco() * quantidade);

                itemPedido.adicionarItem(produtoEscolhido, quantidade);
            }
        }
    }
    public void exibirPedido() {
        // Lógica para exibir o pedido
    }
}