import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Produto implements GerenciadorCadastro<Produto> {
    private String nome;
    private double precoInicial;
    private int quantidade;
    private List<Produto> produtos = new ArrayList<>();

    public Produto() {
    }

    public Produto(String nome, double precoInicial, int quantidade) {
        this.nome = nome;
        this.precoInicial = precoInicial;
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPrecoInicial() {
        return precoInicial;
    }

    public void setPrecoInicial(double precoInicial) {
        this.precoInicial = precoInicial;
    }

    @Override
    public void cadastrar(Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Preço: ");
        double preco = scanner.nextDouble();

        System.out.println("Quantidade: ");
        int quantidade = scanner.nextInt();

        Produto produto = new Produto(nome, preco, quantidade);
        salvar(produto);
        System.out.println("Produto cadastrado com sucesso!");
    }

    @Override
    public void cadastrar(Produto produto) {
        salvar(produto);
    }

    @Override
    public void salvar(Produto produto) {
        produtos.add(produto);
    }

    @Override
    public void listar() {
        System.out.println("Lista de produtos:");
        for (int i = 0; i < produtos.size(); i++) {
            System.out.println(i + " - " + produtos.get(i));
        }
    }

    @Override
    public void atualizar() {
        listar();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Escolha o índice do produto a ser atualizado: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        if (index < 0 || index >= produtos.size()) {
            System.out.println("Índice inválido.");
            return;
        }

        Produto produto = produtos.get(index);

        System.out.print("Novo nome: ");
        String nome = scanner.nextLine();
        if (!nome.isEmpty()) {
            produto.setNome(nome);
        }

        System.out.print("Novo preço: ");
        String precoInput = scanner.nextLine();
        if (!precoInput.isEmpty()) {
            double preco = Double.parseDouble(precoInput);
            produto.setPrecoInicial(preco);
        }

        System.out.print("Nova quantidade: ");
        String quantidadeInput = scanner.nextLine();
        if (!quantidadeInput.isEmpty()) {
            int quantidade = Integer.parseInt(quantidadeInput);
            produto.setQuantidade(quantidade);
        }

        System.out.println("Produto atualizado com sucesso!");
    }

    @Override
    public String toString() {
        return nome + " - R$" + precoInicial + " - estoque: " + quantidade;
    }
}
