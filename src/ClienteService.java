import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Serviço que contém as regras de negócio para as operações de Cliente.
 */
public class ClienteService {

    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public void cadastrarCliente(Scanner scanner) {
        System.out.println("\n--- Cadastro de Novo Cliente ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("RG (Documento Obrigatório): ");
        String rg = scanner.nextLine();

        System.out.print("E-mail: ");
        String email = scanner.nextLine();

        if (rg.isEmpty()) {
            System.out.println("Erro: O RG é obrigatório. Cadastro cancelado.");
            return;
        }

        Cliente novoCliente = new Cliente(nome, rg, email);
        clienteRepository.salvar(novoCliente);
        System.out.println("Cliente cadastrado com sucesso!");
    }

    public void listarClientes() {
        System.out.println("\n--- Lista de Clientes ---");
        List<Cliente> clientes = clienteRepository.buscarTodos();

        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }

        for (int i = 0; i < clientes.size(); i++) {
            System.out.println(i + " - " + clientes.get(i));
        }
    }

    public void atualizarCliente(Scanner scanner) {
        listarClientes();

        if (clienteRepository.buscarTodos().isEmpty()){
            return;
        }

        try {
            System.out.print("\nEscolha o índice do cliente que deseja atualizar: ");
            int index = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer

            Cliente cliente = clienteRepository.buscarPorIndice(index);

            if (cliente == null) {
                System.out.println("Índice inválido.");
                return;
            }

            System.out.println("Deixe em branco para não alterar.");
            System.out.print("Novo nome (" + cliente.getNome() + "): ");
            String novoNome = scanner.nextLine();
            if (!novoNome.isEmpty()) {
                cliente.setNome(novoNome);
            }

            System.out.print("Novo RG (" + cliente.getRg() + "): ");
            String novoRg = scanner.nextLine();
            if (!novoRg.isEmpty()) {
                cliente.setRg(novoRg);
            }

            System.out.print("Novo e-mail (" + cliente.getEmail() + "): ");
            String novoEmail = scanner.nextLine();
            if (!novoEmail.isEmpty()) {
                cliente.setEmail(novoEmail);
            }

            System.out.println("Cliente atualizado com sucesso!");

        } catch (InputMismatchException e) {
            System.out.println("Erro: Índice inválido. Por favor, digite um número.");
            scanner.nextLine();
        }
    }
}