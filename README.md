# english-app

A personal English learning app that helps you review expressions efficiently with spaced repetition.

## Tech stack

| Layer | Technology |
|---|---|
| Frontend | React, TypeScript, Vite |
| Backend | Java 25, Spring Boot, Spring Data JPA |
| Database | PostgreSQL, Flyway |
| Scheduling | [java-fsrs](https://github.com/open-spaced-repetition/java-fsrs) |
| Infrastructure | Docker Compose, nginx |

## Architecture
 
```
Browser
  └─ Web server (Vite in development / nginx in production)
       ├─ Serves the frontend
       └─ Proxies /api/* to the backend
            └─ Backend (Spring Boot)
                 └─ Database (PostgreSQL)
```

## Getting started

### Prerequisites

- Docker and Docker Compose

### Development (with hot reload)

```bash
docker compose up -d --build
```

Open http://localhost:5173.

`docker-compose.override.yml` is loaded automatically and runs the Vite dev server,
so changes in `frontend/` are reflected immediately.

### Production-like setup

```bash
docker compose -f docker-compose.yml up -d --build
```

Open http://localhost:3000.

The frontend is built and served by nginx.

## Contributing
 
See [CONTRIBUTING.md](CONTRIBUTING.md) for commit message conventions.