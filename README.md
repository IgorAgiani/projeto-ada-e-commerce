# Projeto de Estudos Ada Tech - E-Commerce

![Status](https://img.shields.io/badge/Status-Em%20Construção-orange)

Sistema de gerenciamento de clientes, produtos e vendas, desenvolvido como um case de estudo para a imersão backend da Ada Tech. O projeto foi construído em Java com foco na aplicação prática dos **princípios SOLID** e de uma arquitetura limpa e desacoplada (Service-Repository).

---

## 🚀 Funcionalidades Principais

O ADA Commerce simula um sistema de e-commerce via console com um ciclo de vida de venda completo:

### 👥 **Clientes**
- **Cadastrar** novos clientes com nome, documento e e-mail.
- **Listar** todos os clientes cadastrados.
- **Atualizar** as informações de um cliente existente.

### 📦 **Produtos**
- **Cadastrar** novos produtos com nome, preço e quantidade em estoque.
- **Listar** todos os produtos disponíveis.
- **Atualizar** as informações de um produto.

### 💳 **Fluxo de Venda (Pedido)**
- **Criação de Pedido:** Iniciar uma nova venda associada a um cliente.
- **Gerenciamento de Itens:** Adicionar produtos ao pedido, alterar a quantidade e remover itens enquanto o pedido está com status "ABERTO".
- **Ciclo de Vida do Pedido:** Acompanhar o pedido através de seus status, aplicando regras de negócio em cada transição:
  1.  **ABERTO** ➡️ **AGUARDANDO PAGAMENTO:** Finalização do pedido, validando se há itens no carrinho.
  2.  **AGUARDANDO PAGAMENTO** ➡️ **PAGO:** Simulação do processamento de pagamento.
  3.  **PAGO** ➡️ **FINALIZADO:** Simulação da entrega do pedido.
- **Notificações:** Simulação de notificações por e-mail ao cliente em cada mudança de status do pedido.

---

## 🛠️ Tecnologias e Conceitos

- **Java 21**
- **Programação Orientada a Objetos**
- **Princípios SOLID**
- **Design Patterns:** Service Layer, Repository, Dependency Injection
- **Generics**

---

## ▶️ Como Executar

1.  Clone o repositório.
2.  Navegue até a pasta `src`.
3.  Compile todos os arquivos `.java`:
    ```bash
    javac */*/*/*/*.java */*/*/*/*/*.java *.java 
    ```
    *(Nota: O comando pode variar ligeiramente dependendo do seu sistema operacional e da forma como você organiza os pacotes)*
4.  Execute a classe `Main`:
    ```bash
    java br.com.adatech.ecommerce.Main
    ```

---

## 🏛️ Arquitetura e Conceitos Aplicados

O projeto foi estruturado em camadas para seguir o **Princípio da Responsabilidade Única (SRP)** e promover baixo acoplamento.

- **`view`**: Camada responsável pela interação com o usuário (`MenuPrincipal`).
- **`service`**: Camada que contém as regras de negócio e orquestra as operações (`ClienteService`, `ProdutoService`, `PedidoService`).
- **`repository`**: Camada de acesso a dados, que simula um banco de dados em memória (`ClienteRepository`, `ProdutoRepository`).
- **`model`**: Camada que representa as entidades do sistema (`Cliente`, `Produto`, `Pedido`).

### Princípios SOLID

Aqui detalhamos onde cada princípio do SOLID foi aplicado no projeto:

**1. (S) Single Responsibility Principle (Princípio da Responsabilidade Única)**
* **Definição:** Uma classe deve ter apenas um motivo para mudar.
* **No Projeto:** Este é o princípio mais evidente na nossa arquitetura.
    * A classe `Produto` só tem a responsabilidade de representar os dados de um produto.
    * A classe `ProdutoRepository` tem a única responsabilidade de gerenciar a "tabela" de produtos em memória.
    * A classe `ProdutoService` tem a única responsabilidade de orquestrar as regras de negócio relacionadas a produtos.
    * A classe `MenuPrincipal` tem a única responsabilidade de interagir com o usuário.

**2. (O) Open/Closed Principle (Princípio Aberto/Fechado)**
* **Definição:** As entidades de software devem ser abertas para extensão, mas fechadas para modificação.
* **No Projeto:** A interface `GerenciadorCadastro<T>` permite que o sistema seja estendido. Se amanhã precisarmos adicionar um `FornecedorService`, bastaria que ele implementasse a interface. O `MenuPrincipal` poderia interagir com ele sem precisar de grandes modificações em seu código existente.

**3. (L) Liskov Substitution Principle (Princípio da Substituição de Liskov)**
* **Definição:** Tipos derivados devem ser substituíveis por seus tipos base sem alterar o funcionamento do programa.
* **No Projeto:** Uma instância de `ClienteService` pode ser usada em qualquer lugar onde um `GerenciadorCadastro<Cliente>` é esperado, pois `ClienteService` implementa corretamente todos os métodos do contrato da interface.

**4. (I) Interface Segregation Principle (Princípio da Segregação de Interfaces)**
* **Definição:** Os clientes não devem ser forçados a depender de interfaces que não utilizam.
* **No Projeto:** Nossa interface `GerenciadorCadastro` é enxuta e específica para as operações de cadastro. Não a "poluímos" com métodos que não fizessem sentido para todos os seus implementadores, como `processarPagamento()` ou `gerarNotaFiscal()`.

**5. (D) Dependency Inversion Principle (Princípio da Inversão de Dependência)**
* **Definição:** Módulos de alto nível não devem depender de módulos de baixo nível. Ambos devem depender de abstrações.
* **No Projeto:** Este princípio foi aplicado através de **Injeção de Dependência**.
    * O `PedidoService` (módulo de alto nível) não cria suas próprias instâncias de `ClienteRepository` ou `ProdutoRepository` (módulos de baixo nível).
    * Em vez disso, ele recebe essas dependências (abstrações de repositórios) através de seu construtor. Isso desacopla o serviço da implementação concreta do repositório, facilitando a manutenção e os testes.

### Uso de Generics

**O que é?** Generics permitem que classes, interfaces e métodos operem com tipos como parâmetros, promovendo a reutilização de código de forma segura.

**No Projeto:** O principal exemplo é a interface `GerenciadorCadastro<T>`.

```java
// Local: br.com.adatech.ecommerce.service.GerenciadorCadastro.java
public interface GerenciadorCadastro<T> {
    void cadastrar(Scanner scanner);
    void listar();
    void atualizar(Scanner scanner);
}
```

**Reutilização:** Em vez de criarmos uma interface para Cliente e outra para Produto, criamos uma única interface genérica que serve para qualquer entidade.

**Type Safety (Segurança de Tipo)**: Ao declarar class ClienteService implements GerenciadorCadastro<Cliente>, garantimos em tempo de compilação que todos os métodos genéricos T serão substituídos pelo tipo Cliente, evitando erros.
