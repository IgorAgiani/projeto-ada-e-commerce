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

    public void cadastrar(Cliente cliente) {
        salvar(cliente);
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
        listar();
        System.out.print("Escolha o índice do cliente que deseja atualizar: ");
        Scanner scanner = new Scanner(System.in);
        int indice = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        if (indice >= 0 && indice < clientes.size()) {
            Cliente cliente = clientes.get(indice);

            System.out.print("Novo nome: ");
            String novoNome = scanner.nextLine();
            if (!novoNome.isEmpty()) {
                cliente.setNome(novoNome);
            }

            System.out.print("Novo RG: ");
            String novoRg = scanner.nextLine();
            if (!novoRg.isEmpty()) {
                cliente.setRg(novoRg);
            }

            System.out.print("Novo e-mail: ");
            String novoEmail = scanner.nextLine();
            if (!novoEmail.isEmpty()) {
                cliente.setEmail(novoEmail);
            }

            System.out.println("Cliente atualizado com sucesso!");
        } else {
            System.out.println("Índice inválido.");
        }
    }

    @Override
    public String toString() {
        return nome + " - RG: " + rg + " - E-mail: " + email;
    }
}
