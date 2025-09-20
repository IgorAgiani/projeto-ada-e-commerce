package br.com.adatech.ecommerce.service;

import java.util.Scanner;

public interface GerenciadorCadastro <T> {
    void cadastrar(Scanner scanner);
    void listar();
    void atualizar(Scanner scanner);
}
