# Spring Cloud microservices (Netflix OSS)

A product catalogue split into microservices with the classic Spring Cloud Netflix stack: service discovery, centralized configuration, an API gateway, OAuth2 with JWT, declarative HTTP clients and distributed tracing.

> **Legacy stack.** Built on Spring Boot 2.3, Spring Cloud Hoxton and Java 8. Zuul, Hystrix, Ribbon and `spring-cloud-starter-oauth2` have since been removed from Spring Cloud. A current version would use Spring Cloud Gateway, Resilience4j, Spring Authorization Server and Micrometer Tracing. The repository is kept as a reference for the patterns.

## Architecture

```
                    ┌──────────────┐
  client ──────────▶│ zuul-server  │  :8090  gateway, routes /api/products, /api/items, auth
                    └──────┬───────┘
          ┌────────────────┼──────────────────┐
          ▼                ▼                  ▼
 ┌─────────────────┐ ┌──────────────┐ ┌──────────────────────┐
 │ item-service    │ │ product-     │ │ authentication-      │
 │ :8005           │─▶ service      │ │ service              │
 │ Feign client    │ │ MySQL        │ │ OAuth2 + JWT, PG     │
 └─────────────────┘ └──────────────┘ └──────────────────────┘

 eureka-server :8761   service discovery
 config-server :8888   configuration from a Git repo (see Properties/)
 zipkin :9411 + RabbitMQ   distributed tracing (Sleuth)
```

| Module | Role |
|---|---|
| `springboot-eureka-server` | Service registry (Eureka) |
| `springboot-config-server` | Spring Cloud Config. Serves the files in `Properties/` from a Git repository |
| `springboot-zuul-server` | API gateway. Routes requests and validates the JWT issued by the authentication service |
| `springboot-authentication-service` | OAuth2 authorization server. Issues JWTs with extra claims and blocks accounts after repeated failed logins |
| `springboot-product-service` | Product CRUD with JPA on MySQL. Runs on a random port so several instances can register |
| `springboot-item-service` | Builds items from products through a Feign client. Hystrix is enabled, but the fallback on the controller is commented out |

## Run it

Each module builds a Docker image (`<name>:v1`, see each `Dockerfile`). Then start everything with Docker Compose:

```bash
cd springboot-<module> && ./mvnw clean package -DskipTests && docker build -t <module>:v1 .
cd docker-compose && docker compose up -d
```

The config server reads its Git URI from `springboot-config-server/src/main/resources/application.properties`. Point it to a repository that holds the files in `Properties/`.

The passwords and OAuth2 client secret in `docker-compose/` and `Properties/` are **local development defaults only**.

## API

`MicroServices.postman_collection.json` has the requests for getting a token, and for products and items through the gateway.
