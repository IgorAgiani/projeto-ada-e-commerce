# 🛒 ADA Commerce

Sistema simples de gerenciamento de clientes, produtos e pedidos, desenvolvido em Java com foco em boas práticas de orientação a objetos. Ideal para fins educacionais ou como base para sistemas comerciais mais robustos.

## 📘 Visão Geral

O ADA Commerce simula um sistema de comércio com funcionalidades para:

- Cadastro, listagem e atualização de **clientes**
- Cadastro, listagem e atualização de **produtos**
- Estrutura de **pedidos** e **itens de pedido** (em desenvolvimento)
- Interface de terminal para interação com o usuário

## 🧩 Estrutura do Projeto

| Arquivo / Classe         | Descrição                                                                 |
|--------------------------|---------------------------------------------------------------------------|
| `Main.java`              | Ponto de entrada da aplicação                                             |
| `br.com.adatech.ecommerce.view.MenuPrincipal.java`     | Interface de usuário via terminal para gerenciar clientes e produtos      |
| `br.com.adatech.ecommerce.model.Cliente.java`           | Gerencia dados de clientes (nome, RG, e-mail)                             |
| `br.com.adatech.ecommerce.model.Produto.java`           | Gerencia produtos com nome, preço e estoque                               |
| `br.com.adatech.ecommerce.model.Pedido.java`            | Representa uma compra feita por um cliente com lista de itens             |
| `br.com.adatech.ecommerce.model.ItemPedido.java`        | Representa cada item dentro de um pedido (produto, quantidade, preço)     |
| `br.com.adatech.ecommerce.service.GerenciadorCadastro.java`| Interface genérica para operações CRUD                                   |

## 🔄 Uso de Interfaces e Generics

O projeto utiliza a interface genérica `br.com.adatech.ecommerce.service.GerenciadorCadastro<T>` para padronizar operações de cadastro, listagem, atualização e salvamento. Isso promove reutilização de código e facilita a manutenção.

### Interface

```java
public interface br.com.adatech.ecommerce.service.GerenciadorCadastro<T> {
    void cadastrar(Scanner scanner);
    void cadastrar(T entidade);
    void salvar(T entidade);
    void listar();
    void atualizar();
}
```

## Funcionalidades

👥 Clientes
Cadastrar novo cliente

Listar clientes existentes

Atualizar dados de clientes

📦 Produtos
Cadastrar novo produto

Listar produtos em estoque

Atualizar dados de produtos

⚠️ A funcionalidade de vendas (menuVenda) está preparada para implementação futura.

🛠️ Tecnologias Utilizadas
Java 17+

Programação Orientada a Objetos

Terminal / Console para interação

💡 Melhorias Futuras
Implementar o método menuVenda() para registrar pedidos reais

Persistência de dados com arquivos ou banco de dados

Validações mais robustas (ex: evitar RG duplicado)

Interface gráfica com JavaFX ou Swing

Testes automatizados com JUnit

👨‍💻 Autor
Projeto desenvolvido por Igor, com foco em aprendizado e prática de Java orientado a objetos.
