# CRUD com DynamoDB usando Java e Spring Web
Esta é uma aplicação simples que implementa um CRUD utilizando Java e Spring Web, com o DynamoDB como banco de dados. Para facilitar o desenvolvimento e testes, o LocalStack é utilizado para simular o serviço DynamoDB da AWS localmente.


⚠️ Attention!!! Localstack must be configured and running along with Docker Desktop.

⚠️ Atenção!!! O Localstack deve estar configurado e rodando junto com o Docker Desktop.

## Installation

1. Clone the repository:

```bash
git clone https://github.com/Igorgcf/CRUD-with-DynamoDB.git
```

2. Install dependencies with Maven

## Usage

1. Start the application with Maven
2. The API will be accessible at http://localhost:8080

## API Endpoints
The API provides the following endpoints:

**POST PLAYER & SCORE**
```markdown
POST /v1/players/{username}/games - Register a new player end score into the app.
```
```markdown
Body: "score" : 100
return HTTP Status 200 OK
```

**GET PLAYERS by USERNAME**
```markdown
GET /v1/players/{username}/games - Retrieve a list of players filtered by username.
```
```json
   [
    {
        "username": "bruno",
        "gameId": "6a5a3174-5fc3-4dec-a9e2-a0649ebebde0",
        "score": 500.0,
        "createdAt": "2025-03-22T15:18:59.384259700Z"
    }
]
```

**GET PLAYERS by ID**
```markdown
GET /v1/players/{username}/games/{id}{
```
```json
{
    "username": "bruno",
    "gameId": "6a5a3174-5fc3-4dec-a9e2-a0649ebebde0",
    "score": 500.0,
    "createdAt": "2025-03-22T15:18:59.384259700Z"
}
```

**PUT PLAYER by ID**
```markdown
PUT /v1/players/{username}/games/{id}
```
```markdown
return HTTP Status 204 No Content
```

**DELETE PLAYER by ID**
```markdown
DELETE /v1/players/{username}/games/{id}
```
```markdown
return HTTP Status 204 No Content
```

## Database
The project utilizes [DynamoDB](https://docs.aws.amazon.com/dynamodb/) as the database.

## Technologies Used

Java version 21

Spring Boot (Spring Web)

AWS SDK para integração com o DynamoDB

LocalStack para rodar o DynamoDB localmente

Docker (para rodar o LocalStack)

Maven para gerenciamento de dependências

## Contributing

Contributions are welcome! If you find any issues or have suggestions for improvements, please open an issue or submit a pull request to the repository.

When contributing to this project, please follow the existing code style, [commit conventions](https://www.conventionalcommits.org/en/v1.0.0/), and submit your changes in a separate branch.

Contribuições são bem-vindas! Se você encontrar algum problema ou tiver sugestões de melhorias, abra um problema ou envie uma solicitação pull ao repositório.

Ao contribuir para este projeto, siga o estilo de código existente, [convenções de commit](https://medium.com/linkapi-solutions/conventional-commits-pattern-3778d1a1e657), e envie suas alterações em uma branch separado.

![image](https://devio2023-media.developers.io/wp-content/uploads/2023/08/amazon-dynamodb.png)
