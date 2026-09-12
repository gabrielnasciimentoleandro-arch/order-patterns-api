# Order Patterns API

API REST educacional para demonstrar **Design Patterns em Java com Spring Boot**.

## Objetivo

O projeto aplica padrões de projeto a um fluxo simples de pedidos. A primeira versão permite cadastrar produtos e gerar uma cotação de pedido, validando estoque e calculando desconto conforme o tipo de cliente.

## Padrões utilizados

- **Strategy:** `DiscountStrategy` possui implementações para cliente comum e VIP. Novas regras de desconto podem ser adicionadas sem alterar a fachada.
- **Facade:** `OrderFacade` coordena busca de produtos, validação de estoque, cálculo do subtotal e seleção da estratégia de desconto.
- **Repository:** `ProductRepository` abstrai o acesso aos dados com Spring Data JPA.
- **Singleton gerenciado pelo Spring:** controllers, services e strategies são beans gerenciados pelo container, normalmente com escopo singleton.

## Tecnologias

- Java 21
- Spring Boot 3.5.6
- Spring Web
- Spring Data JPA
- H2 Database
- Bean Validation
- Maven Wrapper

## Executar

```bash
./mvnw spring-boot:run
```

Executar os testes:

```bash
./mvnw test
```

## Endpoints

### Criar produto

`POST /api/products`

```json
{
  "name": "Teclado mecânico",
  "price": 350.00,
  "stock": 10
}
```

### Listar produtos

`GET /api/products`

### Consultar produto

`GET /api/products/{id}`

### Gerar cotação de pedido

`POST /api/orders/quote`

```json
{
  "customerType": "VIP",
  "items": [
    { "productId": 1, "quantity": 2 }
  ]
}
```

Resposta esperada para um subtotal de 700.00:

```json
{
  "subtotal": 700.00,
  "discount": 70.00,
  "total": 630.00
}
```

O H2 é em memória e o console fica disponível em `/h2-console` durante a execução local.

## Próximas evoluções

- Persistir pedidos e itens de pedido;
- adicionar tratamento padronizado de erros;
- criar testes unitários e de integração;
- adicionar documentação OpenAPI/Swagger;
- criar autenticação e autorização;
- trocar H2 por PostgreSQL em ambiente de produção.
