package ar.edu.utn.frba.dds.pepita;

import ar.edu.utn.frba.dds.pepita.golondrina.Golondrina;
import io.github.cdimascio.dotenv.Dotenv;
import org.uqbarproject.jpa.java8.extras.EntityManagerConfig;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class EjemploJob implements EntityManagerOps, TransactionalOps, WithGlobalEntityManager {
  public static void main(String[] args) {
    System.out.println("=============== CRON JOB STARTED ===============");

    Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

    new EntityManagerConfig()
        .setConnectionUrl(String.format("jdbc:postgresql://%s/%s",
            dotenv.get("POSTGRES_SERVICE"),
            dotenv.get("POSTGRES_DB")))
        .setConnectionUsername(dotenv.get("POSTGRES_USER"))
        .setConnectionPassword(dotenv.get("POSTGRES_PASSWORD"))
        .applyConfiguration();

    new EjemploJob().run();

    System.out.println("=============== CRON JOB COMPLETED ===============");
    System.exit(0);
  }

  private void run() {
    List<Golondrina> golondrinas = IntStream.range(0, 10)
        .mapToObj(this::createGolondrina)
        .collect(Collectors.toList());
    withTransaction(() -> golondrinas.forEach(this::persist));
    golondrinas.forEach(System.out::println);
  }

  private Golondrina createGolondrina(int i) {
    return new Golondrina(ThreadLocalRandom.current().nextInt(0, 100));
  }
}
