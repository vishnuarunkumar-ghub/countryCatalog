# API Testing Guide

This guide is only an example checklist for testing the API. Use it with Postman, Bruno, curl, or another API testing client.

## Setup

Start the application:

```powershell
.\mvnw.cmd spring-boot:run
```

Base URL:

```text
http://localhost:8080
```

## Example Functional Test Scenarios

| # | Request | Expected result |
|---|---|---|
| 1 | `GET /countries` | Returns HTTP 200 and 4 countries. |
| 2 | `GET /countries/1/cities?page=0&size=10` | Returns first page of India cities with `totalItems = 20`, `totalPages = 2`, `first = true`, `last = false`. |
| 3 | `GET /countries/1/cities?page=1&size=10` | Returns second page of India cities with `first = false`, `last = true`. |
| 4 | `GET /countries/2/cities?page=0&size=100` | Returns Germany cities with `totalItems = 15`. |
| 5 | `GET /countries/3/cities?page=0&size=100` | Returns United States cities with `totalItems = 15`. |
| 6 | `GET /countries/4/cities?page=0&size=100` | Returns Luxembourg cities with `totalItems = 15`. |
| 7 | `GET /cities/1` | Returns Bengaluru details with `countryId = 1`. |
| 8 | `GET /cities/999` | Returns HTTP 404 and `City not found: 999`. |
| 9 | `GET /countries/999/cities?page=0&size=10` | Returns HTTP 404 and `Country not found: 999`. |
| 10 | `GET /countries/1/cities?page=-1&size=10` | Returns HTTP 400 and `page must be greater than or equal to 0`. |
| 11 | `GET /countries/1/cities?page=0&size=0` | Returns HTTP 400 and `size must be between 1 and 100`. |
| 12 | `GET /openapi.yaml` | Returns HTTP 200 and includes the documented API paths. |

## Curl Smoke Checks

```powershell
curl http://localhost:8080/countries
curl "http://localhost:8080/countries/1/cities?page=0&size=10"
curl http://localhost:8080/cities/1
curl http://localhost:8080/openapi.yaml
```

Use the example scenario table above to compare each response status code and response body against the expected result.
