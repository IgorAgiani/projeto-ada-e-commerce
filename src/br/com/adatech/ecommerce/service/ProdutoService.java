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
        System.out.println("\n--- Cadastro de Novo br.com.adatech.ecommerce.model.Produto ---");
        try {
            System.out.print("Nome do br.com.adatech.ecommerce.model.Produto: ");
            String nome = scanner.nextLine();

            System.out.print("Quantidade em estoque: ");
            int quantidade = scanner.nextInt();

            System.out.print("Preço: ");
            double preco = scanner.nextDouble();
            scanner.nextLine();

            if (nome.isEmpty()) {
                System.out.println("Nome do produto é obrigatório. Cadastro cancelado.");
                return;
            }

            Produto produto = new Produto(nome, preco, quantidade);
            produtoRepository.salvar(produto);
            System.out.println("br.com.adatech.ecommerce.model.Produto cadastrado com sucesso!");

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
        for (int i = 0; i < produtos.size(); i++) {
            System.out.println(i + " - " + produtos.get(i));
        }
    }

    public void atualizar(Scanner scanner) {
        listar();

        if (produtoRepository.buscarTodos().isEmpty()) {
            return;
        }

        try {
            System.out.print("\nEscolha o índice do produto que deseja atualizar: ");
            int index = scanner.nextInt();
            scanner.nextLine();

            Produto produtoAntigo = produtoRepository.buscarPorIndice(index);
            if (produtoAntigo == null) {
                System.out.println("Índice inválido.");
                return;
            }

            System.out.println("Deixe em branco para não alterar.");

            System.out.print("Novo nome (atual: " + produtoAntigo.nome() + "): ");
            String novoNomeInput = scanner.nextLine();

            System.out.print("Nova quantidade (atual: " + produtoAntigo.quantidade() + "): ");
            String novaQuantidadeInput = scanner.nextLine();

            System.out.print("Novo preço (atual: " + produtoAntigo.precoInicial() + "): ");
            String novoPrecoInput = scanner.nextLine();

            String nomeFinal = novoNomeInput.isEmpty() ? produtoAntigo.nome() : novoNomeInput;

            int quantidadeFinal = novaQuantidadeInput.isEmpty()
                    ? produtoAntigo.quantidade()
                    : Integer.parseInt(novaQuantidadeInput);

            double precoFinal = novoPrecoInput.isEmpty()
                    ? produtoAntigo.precoInicial()
                    : Double.parseDouble(novoPrecoInput);

            Produto produtoAtualizado = new Produto(nomeFinal, precoFinal, quantidadeFinal);

            produtoRepository.atualizar(index, produtoAtualizado);

            System.out.println("Produto atualizado com sucesso!");

        } catch (InputMismatchException e) {
            System.out.println("Erro: Índice inválido. Por favor, digite um número.");
            scanner.nextLine();
        } catch (NumberFormatException e) {
            System.out.println("Erro: Número em formato inválido. A atualização foi cancelada.");
        }
    }
}