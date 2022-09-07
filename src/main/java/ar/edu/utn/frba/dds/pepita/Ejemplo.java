package ar.edu.utn.frba.dds.pepita;

import io.github.cdimascio.dotenv.Dotenv;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import java.util.concurrent.ThreadLocalRandom;

public class Ejemplo implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps {

  public static void main(String[] args) {
    Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

    System.setProperty("jpa.properties.hibernate.connection.url",
        String.format("jdbc:postgresql://%s/%s",
            dotenv.get("POSTGRES_SERVICE"),
            dotenv.get("POSTGRES_DB")));
    System.setProperty("jpa.properties.hibernate.connection.username",
        dotenv.get("POSTGRES_USER"));
    System.setProperty("jpa.properties.hibernate.connection.password",
        dotenv.get("POSTGRES_PASSWORD"));

    new Ejemplo().ejecutar();
  }

  public void ejecutar() {
    withTransaction(() -> {
      persist(new Golondrina(ThreadLocalRandom.current().nextInt(0, 100)));
    });

    withTransaction(() -> {
      createQuery("from Golondrina", Golondrina.class)
          .getResultList()
          .forEach(System.out::println);
    });
  }
}
