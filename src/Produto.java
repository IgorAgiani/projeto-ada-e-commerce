import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Produto implements GerenciadorCadastro<Produto> {
    private String nome;
    private double preco;
    private List<Produto> produtos = new ArrayList<>();

    public Produto() {
    }

    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    @Override
    public void cadastrar(Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Preço: ");
        double preco = scanner.nextDouble();

        Produto produto = new Produto(nome, preco);
        salvar(produto);
        System.out.println("Produto cadastrado com sucesso!");
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
        // Lógica para atualizar o produto na lista
    }

    public Produto escolherPorIndice(int indice) {
        if (indice >= 0 && indice < produtos.size()) {
            return produtos.get(indice);
        }
        return null;
    }

    @Override
    public String toString() {
        return "Produto: " + nome + " - preço: R$" + preco;
    }
}
