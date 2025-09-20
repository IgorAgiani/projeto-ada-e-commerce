public class Produto {
    private String nome;
    private double precoInicial;
    private int quantidade;

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
    public String toString() {
        return nome + " - R$" + precoInicial + " - estoque: " + quantidade;
    }
}
