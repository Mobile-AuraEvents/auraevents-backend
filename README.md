# Auraev Backend

Backend em Spring Boot para gerenciamento de eventos artisticos (uso local/academico).

## Requisitos
- Java 21
- Maven 3.9+

## Como rodar localmente
1. No IntelliJ, abrir o projeto.
2. Executar a classe `AuraevApplication` (botao Run).

Ou via terminal:
```bash
./mvnw spring-boot:run
```

## Banco de dados
- Banco em memoria: H2
- URL: `jdbc:h2:mem:auraevdb`
- Console H2: `http://localhost:8080/h2-console`

## CORS
- Liberado para chamadas locais do frontend.

## Health check
- `GET /api/health`

## Rotas principais
### CRUDs basicos
- `GET/POST /api/casas-de-show`
- `GET/PUT/DELETE /api/casas-de-show/{id}`
- `GET/POST /api/artistas`
- `GET/PUT/DELETE /api/artistas/{id}`
- `GET/POST /api/patrocinadores`
- `GET/PUT/DELETE /api/patrocinadores/{id}`
- `GET/POST /api/veiculos-imprensa`
- `GET/PUT/DELETE /api/veiculos-imprensa/{id}`

### Shows
- `GET/POST /api/shows`
- `GET/PUT/DELETE /api/shows/{id}`
- `POST /api/shows/{id}/patrocinadores`
- `POST /api/shows/{id}/veiculos-imprensa`

### Convidados
- `GET /api/patrocinadores/{patrocinadorId}/convidados`
- `POST /api/patrocinadores/{patrocinadorId}/convidados`
- `PUT /api/convidados/{convidadoId}`
- `DELETE /api/convidados/{convidadoId}`
