import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cliente implements GerenciadorCadastro<Cliente> {
    private String nome;
    private String rg;
    private String email;
    private List<Cliente> clientes = new ArrayList<>();

    public Cliente () {
    }

    public Cliente(String nome, String documentoObrigatorio, String email) {
        this.nome = nome;
        this.rg = documentoObrigatorio;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void cadastrar(Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Número RG (obrigatório): ");
        String documento = scanner.nextLine();

        System.out.print("E-mail: ");
        String email = scanner.nextLine();

        if (documento.isEmpty()) {
            System.out.println("Documento é obrigatório. Cadastro cancelado.");
            return;
        }

        Cliente cliente = new Cliente(nome, documento, email);
        salvar(cliente);
        System.out.println("Cliente cadastrado com sucesso!");
    }

    @Override
    public void salvar(Cliente cliente) {
        clientes.add(cliente);
    }

    @Override
    public void listar() {
        System.out.println("Lista de clientes:");
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println(i + " - " + clientes.get(i));
        }
    }

    @Override
    public void atualizar() {
        // logica para atualizar clientes
    }

    public Cliente escolherPorIndice(int numero) {
        if (numero >= 0 && numero < clientes.size()) {
            return clientes.get(numero);
        } else {
            System.out.println("Índice inválido.");
            return null;
        }
    }

    @Override
    public String toString() {
        return "Nome: " + nome + " - RG: " + rg + "\nE-mail: " + email;
    }

}
