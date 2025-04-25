# Documentação - Solução Técnica

> Essa API possui endpoints para criação de transações, e consulta de transações e taxa de conversão atual.

# Stack utilizada

* * * * *

- Java 11
- Spring Boot 2.7.2
- JPA Hibernate
- PostgreSQL

# Arquitetura

* * * * *
- A arquitetura escolhida foi a Hexagonal, dividindo a implementação entre adapters, domain e usecases.

# Banco de dados - Schema

* * * * *

O schema do banco foi gerado através da ferramenta https://sqldbm.com para o banco PostgreSQL.
O .sql gerado está disponível em resources/schema.sql

![DB](https://github.com/ViniciusSambinello/srm-challenge/blob/main/DATABASE.png)

Endpoints de transações
=================

* * * * *

### [POST] - /api/v1/realms/v1/transactions

Registra uma nova transação na API

|                     |        Nome        |       Descriçao       | Obrigatorio |
|:-------------------:|:------------------:|:---------------------:|:-----------:|
| Corpo da requisição |     product_id     |     Id do produto     |     sim     |
| Corpo da requisição |  product_quantity  | Quantidade do produto |     sim     |
| Corpo da requisição |       origin       |    Reino de Origem    |     sim     |
| Corpo da requisição |  origin_realm_id   |      Id do Reino      |     sim     |
| Corpo da requisição | origin_currency_id |    Moeda do Reino     |     sim     |
| Corpo da requisição |       target       |   Reino de Destino    |     sim     |
| Corpo da requisição |  target_realm_id   |      Id do Reino      |     sim     |
| Corpo da requisição | target_currency_id |    Moeda do Reino     |     sim     |

#### Corpo de exemplo

```json
{
  "data": {
    "product_id": "1",
    "product_quantity": 1,
    "origin": {
      "realm_id": "1",
      "currency_id": "1"
    },
    "target": {
      "realm_id": "2",
      "currency_id": "2"
    }
  }
}
```

### [GET] - /api/v1/realms/{realm_id}/transactions

Consulta as transações por reino

|                     |           Nome           |           Descriçao           | Obrigatorio |
|:-------------------:|:------------------------:|:-----------------------------:|:-----------:|
| Corpo da requisição |            id            |        Id da transação        |     sim     |
| Corpo da requisição |        product_id        |         Id do Produto         |     sim     |
| Corpo da requisição |       product_name       |        Nome do Produto        |     sim     |
| Corpo da requisição |     origin_realm_id      |     Id Do Reino de Origem     |     sim     |
| Corpo da requisição |    origin_realm_name     |    Nome do Reino de Origem    |     sim     |
| Corpo da requisição |    origin_currency_id    |     Id da Moeda de Origem     |     sim     |
| Corpo da requisição |   origin_currency_name   |    Nome da Moeda de Origem    |     sim     |
| Corpo da requisição | origin_transaction_value | Valor da Transação de Origem  |     sim     |
| Corpo da requisição |     target_realm_id      |    Id do Reino de Destino     |     sim     |
| Corpo da requisição |    target_realm_name     |   Nome do Reino de Destino    |     sim     |
| Corpo da requisição |    target_currency_id    |    Id da Moeda de Destino     |     sim     |
| Corpo da requisição |   target_currency_name   |   Nome da Moeda de Destino    |     sim     |
| Corpo da requisição | target_transaction_value | Valor da Transação de Destino |     sim     |
| Corpo da requisição |         created          |  Quando a criação foi feita   |     sim     |
| Corpo da requisição |      exchange_rate       |      A taxa de conversão      |     sim     |

#### Corpo de exemplo

```json
{
  "data": [
    {
      "id": "1",
      "product": {
        "id": "1",
        "name": "Pele"
      },
      "origin": {
        "realm": {
          "id": "1",
          "name": "Wefin"
        },
        "currency": {
          "id": "1",
          "name": "Ouro Real"
        },
        "transaction_value": 1.00
      },
      "target": {
        "realm": {
          "id": "2",
          "name": "Anoes"
        },
        "currency": {
          "id": "2",
          "name": "Tibar"
        },
        "transaction_value": 1.00
      },
      "created": 1000000000000,
      "exchange_rate": 1.00
    }
  ]
}
```

### [GET] - /api/v1/currencies/{currency_id}/rates

Consulta as taxas de conversão por moeda

|                     |        Nome        |     Descriçao     | Obrigatorio |
|:-------------------:|:------------------:|:-----------------:|:-----------:|
| Corpo da requisição |        rate        | Taxa de Conversão |     sim     |
| Corpo da requisição |    currency_id     |    Id da Moeda    |     sim     |

#### Corpo de exemplo

```json
{
  "data": [
    {
      "rate": 2.50,
      "currency_id": "2"
    }
  ]
}
```