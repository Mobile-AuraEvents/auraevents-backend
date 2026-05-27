# Auraev Backend

Backend em Spring Boot para gerenciamento de eventos artisticos (uso local/academico).

## Requisitos
- Java 21
- Maven 3.9+
- PostgreSQL local

## Como rodar localmente
1. Garanta que o PostgreSQL local esteja ativo.
2. Garanta que o banco `auraev` exista.
3. Ajuste usuario/senha no `application.properties` se necessario.
4. No IntelliJ, execute a classe `AuraevApplication`.

Ou via terminal:
```bash
./mvnw spring-boot:run
```

## Banco de dados
- SGBD: PostgreSQL local
- URL: `jdbc:postgresql://localhost:5432/auraev`
- Usuario padrao: `postgres`
- Senha padrao: `postgres`

## CORS
- Liberado para chamadas locais do frontend.

## Health check
- `GET /api/health`

## Rotas principais
- `GET/POST /api/casas-de-show`
- `GET/PUT/DELETE /api/casas-de-show/{id}`
- `GET/POST /api/artistas`
- `GET/PUT/DELETE /api/artistas/{id}`
- `GET/POST /api/patrocinadores`
- `GET/PUT/DELETE /api/patrocinadores/{id}`
- `GET/POST /api/veiculos-imprensa`
- `GET/PUT/DELETE /api/veiculos-imprensa/{id}`
- `GET/POST /api/shows`
- `GET/PUT/DELETE /api/shows/{id}`
- `POST /api/shows/{id}/patrocinadores`
- `POST /api/shows/{id}/veiculos-imprensa`
- `GET /api/patrocinadores/{patrocinadorId}/convidados`
- `POST /api/patrocinadores/{patrocinadorId}/convidados`
- `PUT /api/convidados/{convidadoId}`
- `DELETE /api/convidados/{convidadoId}`
