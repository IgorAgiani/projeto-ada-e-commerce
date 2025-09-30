package br.com.adatech.ecommerce.service;

import br.com.adatech.ecommerce.model.Cliente;
import br.com.adatech.ecommerce.repository.ClienteRepository;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ClienteService implements GerenciadorCadastro<Cliente> {

    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public void cadastrar(Scanner scanner) {
        System.out.println("\n--- Cadastro de Novo br.com.adatech.ecommerce.model.Cliente ---");
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
        System.out.println("br.com.adatech.ecommerce.model.Cliente cadastrado com sucesso!");
    }

    public void listar() {
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

    public void atualizar(Scanner scanner) {
        System.out.print("\nDigite o RG do cliente que deseja atualizar: ");
        String rg = scanner.nextLine();

        Cliente clienteAntigo = clienteRepository.buscarPorRg(rg);

        if (clienteAntigo == null) {
            System.out.println("Erro: Cliente com RG " + rg + " não encontrado.");
            return;
        }

        System.out.println("Deixe em branco para não alterar. Cliente encontrado: " + clienteAntigo.nome());
        System.out.print("Novo nome (" + clienteAntigo.nome() + "): ");
        String novoNome = scanner.nextLine();

        System.out.print("Novo e-mail (" + clienteAntigo.email() + "): ");
        String novoEmail = scanner.nextLine();

        String nomeFinal = novoNome.isEmpty() ? clienteAntigo.nome() : novoNome;
        String emailFinal = novoEmail.isEmpty() ? clienteAntigo.email() : novoEmail;

        Cliente clienteAtualizado = new Cliente(nomeFinal, clienteAntigo.rg(), emailFinal);

        clienteRepository.salvar(clienteAtualizado);

        System.out.println("Cliente atualizado com sucesso!");
    }
}