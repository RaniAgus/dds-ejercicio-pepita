package ar.edu.utn.frba.dds.db;

import ar.edu.utn.frba.dds.pepita.golondrina.Golondrina;
import org.junit.jupiter.api.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GolondrinaTest extends AbstractPersistenceTest implements WithGlobalEntityManager {

  @Test
  void golondrina() {
    persist(new Golondrina(10));

    Golondrina pepita = createQuery("from Golondrina", Golondrina.class)
        .getResultList().get(0);

    assertEquals(10, pepita.getEnergia());
  }
}
