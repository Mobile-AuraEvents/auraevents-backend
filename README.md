# Auraev Backend

Backend em Spring Boot para gerenciamento de eventos artísticos (uso local/acadêmico).

## Requisitos
- Java 21
- Maven 3.9+

## Como rodar localmente
1. No IntelliJ, abrir o projeto.
2. Executar a classe `AuraevApplication` (botão Run).

Ou via terminal:
```bash
./mvnw spring-boot:run
```

## Banco de dados
- Banco em memória: H2
- URL: `jdbc:h2:mem:auraevdb`
- Console H2: `http://localhost:8080/h2-console`
  - JDBC URL: `jdbc:h2:mem:auraevdb`
  - User: `sa`
  - Password: (vazio)

## Endpoint de health check
- `GET http://localhost:8080/api/health`

## CORS
- Liberado para chamadas locais do frontend (`*`) nesta fase inicial.
