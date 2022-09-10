# dds-ejercicio-pepita

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

### Ejecutar en local

1. Levantar solo el Postgres por primera vez para crear la base de datos

```bash
docker compose up db
```

2. Ejecutar desde IntelliJ la clase `Ejemplo`

## Deploy

### Probar en local desde Docker Compose

```bash
docker compose up --build
```

### Railway

1. Ir a https://railway.app/new

2. Hacer click en "Provision PostgreSQL".

3. Seleccionar el container de PostgreSQL y copiar los datos de conexión de la
pestaña "Connect". Los usaremos más adelante.

4. Hacer click en "New" > "GitHub Repo" > Tu repositorio

5. Seleccionar el container del repositorio y luego:

   1. Ir a la pestaña "Variables", completar las variables que comienzan con
   "POSTGRES_" con los datos de conexión y utilizar `PORT=80`.

   2. Ir a la pestaña "Settings" > "Restart Policy" > "Never" para que no se
   quede reiniciando a lo loco.

   3. También en la pestaña "Settings", seleccionar "Start command" y completar
      con el comando para ejecutar el jar-with-dependencies, por ejemplo:

```bash
java -jar ejemplo-1.0-SNAPSHOT-jar-with-dependencies.jar
```

   4. Por último, ir a "Domains" y generar una URL provista por Railway para 
      exponer la aplicación a la web.

¡Eso es todo!
