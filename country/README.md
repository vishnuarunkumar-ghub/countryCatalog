# Countries and Cities API

Simple Spring Boot backend for listing countries, listing paginated cities by selected country, and retrieving city details.
The in-memory catalog includes 20 cities for India and 15 cities each for Germany, the United States, and Luxembourg.

## Requirements

- Java 21
- Git
- Postman, Bruno, curl, or another API client

## Run

```powershell
.\mvnw.cmd spring-boot:run
```

The application starts on `http://localhost:8080`.

## API Documentation

- Swagger UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI contract: `http://localhost:8080/openapi.yaml`
- Generated Springdoc JSON: `http://localhost:8080/v3/api-docs`

## Endpoints

```http
GET /countries
GET /countries/{countryId}/cities?page=0&size=10
GET /cities/{cityId}
```

## Examples

```powershell
curl http://localhost:8080/countries
curl "http://localhost:8080/countries/1/cities?page=0&size=2"
curl http://localhost:8080/cities/1
```

## API Testing

Example manual API scenarios are documented in `docs/API_TESTING.md`.
