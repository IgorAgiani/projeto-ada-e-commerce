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
        listar();

        if (clienteRepository.buscarTodos().isEmpty()) {
            return;
        }

        try {
            System.out.print("\nEscolha o índice do cliente que deseja atualizar: ");
            int index = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer

            Cliente clienteAntigo = clienteRepository.buscarPorIndice(index);

            if (clienteAntigo == null) {
                System.out.println("Índice inválido.");
                return;
            }

            System.out.println("Deixe em branco para não alterar.");
            System.out.print("Novo nome (" + clienteAntigo.nome() + "): ");
            String novoNome = scanner.nextLine();

            System.out.print("Novo RG (" + clienteAntigo.rg() + "): ");
            String novoRg = scanner.nextLine();

            System.out.print("Novo e-mail (" + clienteAntigo.email() + "): ");
            String novoEmail = scanner.nextLine();

            String nomeFinal = novoNome.isEmpty() ? clienteAntigo.nome() : novoNome;
            String rgFinal = novoRg.isEmpty() ? clienteAntigo.rg() : novoRg;
            String emailFinal = novoEmail.isEmpty() ? clienteAntigo.email() : novoEmail;

            Cliente clienteAtualizado = new Cliente(nomeFinal, rgFinal, emailFinal);

            clienteRepository.atualizar(index, clienteAtualizado);

            System.out.println("Cliente atualizado com sucesso!");

        } catch (InputMismatchException e) {
            System.out.println("Erro: Índice inválido. Por favor, digite um número.");
            scanner.nextLine();
        }
    }
}