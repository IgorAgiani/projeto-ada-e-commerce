import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ProdutoService {

    private ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public void cadastrarProduto(Scanner scanner) {
        System.out.println("\n--- Cadastro de Novo Produto ---");
        try {
            System.out.print("Nome do Produto: ");
            String nome = scanner.nextLine();

            System.out.print("Quantidade em estoque: ");
            int quantidade = scanner.nextInt();

            System.out.print("Preço: ");
            double preco = scanner.nextDouble();
            scanner.nextLine();

            if (nome.isEmpty()) {
                System.out.println("Nome do produto é obrigatório. Cadastro cancelado.");
                return;
            }

            Produto produto = new Produto(nome, preco, quantidade);
            produtoRepository.salvar(produto);
            System.out.println("Produto cadastrado com sucesso!");

        } catch (InputMismatchException e) {
            System.out.println("Erro: Entrada inválida. Por favor, insira os dados no formato correto.");
            scanner.nextLine();
        }
    }

    public void listarProdutos() {
        System.out.println("\n--- Lista de Produtos em Estoque ---");
        List<Produto> produtos = produtoRepository.buscarTodos();
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }
        for (int i = 0; i < produtos.size(); i++) {
            System.out.println(i + " - " + produtos.get(i));
        }
    }

    public void atualizarProduto(Scanner scanner) {
        listarProdutos();

        if (produtoRepository.buscarTodos().isEmpty()){
            return;
        }

        try {
            System.out.print("\nEscolha o índice do produto que deseja atualizar: ");
            int index = scanner.nextInt();
            scanner.nextLine();

            Produto produto = produtoRepository.buscarPorIndice(index);
            if (produto == null) {
                System.out.println("Índice inválido.");
                return;
            }

            System.out.println("Deixe em branco para não alterar.");
            System.out.print("Novo nome (atual: " + produto.getNome() + "): ");
            String novoNome = scanner.nextLine();
            if (!novoNome.isEmpty()) {
                produto.setNome(novoNome);
            }

            System.out.print("Nova quantidade (atual: " + produto.getQuantidade() + "): ");
            String novaQuantidadeInput = scanner.nextLine();
            if (!novaQuantidadeInput.isEmpty()) {
                produto.setQuantidade(Integer.parseInt(novaQuantidadeInput));
            }

            System.out.print("Novo preço (atual: " + produto.getPrecoInicial() + "): ");
            String novoPrecoInput = scanner.nextLine();
            if (!novoPrecoInput.isEmpty()) {
                produto.setPrecoInicial(Double.parseDouble(novoPrecoInput));
            }

            System.out.println("Produto atualizado com sucesso!");

        } catch (InputMismatchException e) {
            System.out.println("Erro: Índice inválido. Por favor, digite um número.");
            scanner.nextLine();
        } catch (NumberFormatException e) {
            System.out.println("Erro: Número em formato inválido. A atualização foi cancelada.");
        }
    }
}