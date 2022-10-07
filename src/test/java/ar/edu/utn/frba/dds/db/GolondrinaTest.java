package ar.edu.utn.frba.dds.db;

import ar.edu.utn.frba.dds.pepita.golondrina.Golondrina;
import com.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import com.github.flbulgarelli.jpa.extras.test.PersistenceTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GolondrinaTest implements PersistenceTest, WithSimplePersistenceUnit {

  @Test
  void golondrina() {
    persist(new Golondrina(10));

    Golondrina pepita = createQuery("from Golondrina", Golondrina.class)
        .getResultList().get(0);

    assertEquals(10, pepita.getEnergia());
  }
}
