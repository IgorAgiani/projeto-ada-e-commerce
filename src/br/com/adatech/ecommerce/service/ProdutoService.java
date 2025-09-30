package br.com.adatech.ecommerce.service;

import br.com.adatech.ecommerce.model.Produto;
import br.com.adatech.ecommerce.repository.ProdutoRepository;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ProdutoService implements GerenciadorCadastro<Produto> {

    private ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public void cadastrar(Scanner scanner) {
        System.out.println("\n--- Cadastro de Novo Produto ---");
        try {
            System.out.print("Nome do Produto: ");
            String nome = scanner.nextLine();

            if (produtoRepository.buscarPorNome(nome) != null) {
                System.out.println("Erro: Já existe um produto com o nome '" + nome + "'. Cadastro cancelado.");
                return;
            }

            System.out.print("Quantidade em estoque: ");
            int quantidade = scanner.nextInt();

            System.out.print("Preço: ");
            double preco = scanner.nextDouble();
            scanner.nextLine(); // Limpa o buffer

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

    public void listar() {
        System.out.println("\n--- Lista de Produtos em Estoque ---");
        List<Produto> produtos = produtoRepository.buscarTodos();
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }
        produtos.forEach(System.out::println);
    }

    public void atualizar(Scanner scanner) {
        System.out.print("\nDigite o nome do produto que deseja atualizar: ");
        String nomeProduto = scanner.nextLine();

        Produto produtoAntigo = produtoRepository.buscarPorNome(nomeProduto);

        if (produtoAntigo == null) {
            System.out.println("Erro: Produto '" + nomeProduto + "' não encontrado.");
            return;
        }

        try {
            System.out.println("Deixe em branco para não alterar. Produto encontrado: " + produtoAntigo);
            System.out.print("Novo nome (atual: " + produtoAntigo.nome() + "): ");
            String novoNomeInput = scanner.nextLine();

            System.out.print("Nova quantidade (atual: " + produtoAntigo.quantidade() + "): ");
            String novaQuantidadeInput = scanner.nextLine();

            System.out.print("Novo preço (atual: " + produtoAntigo.precoInicial() + "): ");
            String novoPrecoInput = scanner.nextLine();

            String nomeFinal = novoNomeInput.isEmpty() ? produtoAntigo.nome() : novoNomeInput;
            int quantidadeFinal = novaQuantidadeInput.isEmpty() ? produtoAntigo.quantidade() : Integer.parseInt(novaQuantidadeInput);
            double precoFinal = novoPrecoInput.isEmpty() ? produtoAntigo.precoInicial() : Double.parseDouble(novoPrecoInput);

            Produto produtoAtualizado = new Produto(nomeFinal, precoFinal, quantidadeFinal);

            if (!nomeFinal.equals(produtoAntigo.nome())) {
                produtoRepository.remover(produtoAntigo.nome());
            }

            produtoRepository.salvar(produtoAtualizado);

            System.out.println("Produto atualizado com sucesso!");

        } catch (NumberFormatException e) {
            System.out.println("Erro: Formato de número inválido. A atualização foi cancelada.");
        }
    }
}