package ar.edu.utn.frba.dds.pepita;

import ar.edu.utn.frba.dds.pepita.golondrina.Golondrina;
import com.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class EjemploJob extends ApplicationContext implements WithSimplePersistenceUnit {
  public static void main(String[] args) {
    System.out.println("=============== CRON JOB STARTED ===============");
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
