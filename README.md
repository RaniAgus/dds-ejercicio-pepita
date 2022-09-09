# java-base-project

## Dependencias

- Java 8
- Maven 3.8 o superior
- Docker

## Setup

1. Crear un archivo `.env` basándose en el ejemplo que se encuentra en
`.env.example`

2. Crear un volumen externo en donde guardar los datos y nombrarlo con el 
formato `${NAME}-data`, por ejemplo:

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
