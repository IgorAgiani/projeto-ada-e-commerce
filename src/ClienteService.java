import java.util.List;
import java.util.Scanner;

public class ClienteService {
    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public void cadastrarCliente(Scanner scanner) {
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
        clienteRepository.salvar(cliente); // Usa o repositório!
        System.out.println("Cliente cadastrado com sucesso!");
    }

    public void listarClientes() {
        System.out.println("\n--- Lista de Clientes ---");
        List<Cliente> clientes = clienteRepository.buscarTodos(); // Busca do repositório!
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println(i + " - " + clientes.get(i));
        }
    }

    public void atualizarCliente() {
        listarClientes();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Escolha o índice do cliente que deseja atualizar: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        Cliente cliente = clienteRepository.buscarPorIndice(index); // Busca do repositório!
        if (cliente == null) {
            System.out.println("Índice inválido.");
            return;
        }

        System.out.print("Novo nome (atual: " + cliente.getNome() + "): ");
        String novoNome = scanner.nextLine();
        System.out.print("Novo RG (atual: " + cliente.getRg() + "): ");
        String novoRg = scanner.nextLine();
        System.out.print("Novo e-mail (atual: " + cliente.getEmail() + "): ");
        String novoEmail = scanner.nextLine();

        if (!novoNome.isEmpty()) {
            cliente.setNome(novoNome);
        }
        if (!novoRg.isEmpty()) {
            cliente.setRg(novoRg);
        }
        if (!novoEmail.isEmpty()) {
            cliente.setEmail(novoEmail);
        }

        System.out.println("Cliente atualizado com sucesso!");
    }
}
