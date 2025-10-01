# Projeto de Estudos Ada Tech - E-Commerce

## Visão Geral

Sistema de gerenciamento de clientes, produtos e vendas via console, desenvolvido como um case de estudo para a imersão backend da Ada Tech. O projeto foi construído em Java com foco na aplicação prática dos princípios SOLID e de uma arquitetura limpa (Service-Repository).

Posteriormente, o projeto foi **refatorado** para incorporar técnicas modernas da plataforma Java, como `Records` para imutabilidade, a `Stream API` para processamento de dados e o uso da estrutura `Map` para otimização de performance, além de um aprofundamento em conceitos de encapsulamento.

## Funcionalidades Principais

O sistema simula um e-commerce via console com um ciclo de vida de venda completo:

### Clientes
- **Cadastrar** novos clientes com nome, documento e e-mail.
- **Listar** todos os clientes cadastrados.
- **Atualizar** as informações de um cliente existente.

### Produtos
- **Cadastrar** novos produtos com nome, preço e quantidade em estoque.
- **Listar** todos os produtos disponíveis.
- **Atualizar** as informações de um produto.

### Fluxo de Venda (Pedido)
- **Criação de Pedido:** Iniciar uma nova venda associada a um cliente.
- **Gerenciamento de Itens:** Adicionar produtos ao pedido, alterar a quantidade e remover itens enquanto o pedido está com status "ABERTO".
- **Ciclo de Vida do Pedido:** Acompanhar o pedido através de seus status, aplicando regras de negócio em cada transição:
  1.  **ABERTO** ➡️ **AGUARDANDO PAGAMENTO:** Finalização do pedido, validando se há itens no carrinho.
  2.  **AGUARDANDO PAGAMENTO** ➡️ **PAGO:** Simulação do processamento de pagamento.
  3.  **PAGO** ➡️ **FINALIZADO:** Simulação da entrega do pedido.
- **Notificações:** Simulação de notificações por e-mail ao cliente em cada mudança de status do pedido.

## Funcionalidades a Serem Implementadas

### Sistema de Cupons de Desconto
O sistema será estendido para incluir um módulo completo de gerenciamento de cupons de desconto. As capacidades planejadas são:
* **Criar e Listar:** A plataforma deverá permitir a criação e listagem de cupons de desconto disponíveis.
* **Aplicação:** Os cupons devem poder ser aplicados aos pedidos para reduzir o valor total.
* **Atualização e Expiração:** Será possível atualizar os dados de um cupom e definir uma data de expiração para ele.
* **Validação:** Antes de aplicar um cupom, o sistema deverá realizar validações, incluindo a data de expiração e se o cupom já foi utilizado.

### Mecanismo de Regras de Desconto
Será desenvolvido um sistema flexível para a criação de regras de desconto.
* **Regras Simples:** Permitirão a aplicação de um desconto com valor fixo ou percentual.
* **Regras Compostas:** Possibilitarão a combinação de múltiplas regras para criar cenários mais complexos, como descontos progressivos ou condicionais.

### Aprimoramento do Sistema de Notificações
A funcionalidade existente de notificação por e-mail ao cliente, que já cobre as etapas de finalização, pagamento e entrega do pedido, será aprimorada neste módulo.

## Tecnologias e Conceitos Aplicados

- **Java 21**
- **Programação Orientada a Objetos**
- **Arquitetura em Camadas (View, Service, Repository, Model)**
- **Princípios SOLID**
- **Java Records** para imutabilidade de dados.
- **Java Streams API** para processamento de coleções.
- **Estrutura de Dados `Map`** para otimização de buscas.
- **Injeção de Dependência** manual nos serviços.
- **Generics** para reutilização de código em interfaces.

---

## Modernização e Refatoração Aplicada

A segunda fase do desenvolvimento do projeto focou em refatorar o código base para utilizar recursos modernos do Java e aprofundar conceitos de design de software.

### 1. Java Records para Imutabilidade
As classes do pacote `model` foram convertidas de classes tradicionais para `Records`.
- **O quê?** `Cliente`, `Produto` e `ItemPedido` agora são `records`.
- **Por quê?** Para garantir a **imutabilidade** dos objetos de dados, tornando o estado da aplicação mais previsível e seguro, além de reduzir drasticamente o código repetitivo (*boilerplate*).

### 2. Otimização de Repositórios com `Map`
Os repositórios de `Cliente` e `Produto` foram refatorados para usar `Map` em vez de `List`.
- **O quê?** `ClienteRepository` usa um `Map<String, Cliente>` (chave: RG) e `ProdutoRepository` usa `Map<String, Produto>` (chave: nome).
- **Por quê?** Para otimizar a performance de busca. A busca em uma `List` é uma operação O(n) (lenta, cresce com o número de itens), enquanto a busca em um `Map` por chave é O(1) (instantânea), tornando o sistema muito mais escalável.

### 3. Processamento de Dados com a API de Streams
Loops complexos foram substituídos por `Streams` para um código mais declarativo e legível.
- **O quê?** O principal exemplo é o cálculo do valor total do pedido.
- **Por quê?** A abordagem com `Streams` expressa a *intenção* do código ("some o subtotal de cada item") em vez de detalhar *como* fazer o loop.
  ```java
  // Exemplo em Pedido.java
  public double getValorTotal() {
      return this.itens.stream()
          .mapToDouble(item -> item.quantidade() * item.precoVenda())
          .sum();
  }

### 4. Encapsulamento Avançado na Classe `Pedido`
A classe `Pedido` foi refatorada para ser a única guardiã de suas regras de negócio.
- **O quê?** Métodos como setStatus() tiveram sua visibilidade restrita, e foram criados métodos de comportamento públicos (finalizar(), pagar(), entregar()). O método getItens() passou a retornar uma lista não modificável.
- **Por quê?** Para garantir que o estado do pedido só possa ser alterado de acordo com o ciclo de vida definido nas regras de negócio, tornando a classe mais robusta e protegida contra modificações indevidas.

### 5. Como Executar
- Clone o repositório.
- Abra o projeto em uma IDE Java (como IntelliJ IDEA ou Eclipse).
- Localize a classe `Main.java` no pacote `br.com.adatech.ecommerce.`
- Execute o método `main`.
