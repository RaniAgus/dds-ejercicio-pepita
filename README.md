# java-base-project

## Dependencias

- Java 8
- Maven 3.8 o superior
- Docker

## Setup

1. Crear un archivo `.env` basándose en este ejemplo:

```env
POSTGRES_SERVICE=localhost:5432
POSTGRES_DB=prueba
POSTGRES_USER=jpademo
POSTGRES_PASSWORD=jpademo
```

2. Crear un volumen externo en donde guardar los datos

```bash
docker volume create ejemplo-data
```

## Ejecutar en local

1. Levantar solo el Postgres por primera vez para crear la base de datos

```bash
docker compose up db
```

2. Ejecutar desde IntelliJ la clase `Ejemplo`

## Ejecutar desde Docker

```bash
docker compose up --build
```
