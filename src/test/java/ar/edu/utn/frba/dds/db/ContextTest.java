package ar.edu.utn.frba.dds.db;

import com.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import com.github.flbulgarelli.jpa.extras.test.PersistenceTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ContextTest implements PersistenceTest, WithSimplePersistenceUnit {

  @Test
  public void contextUp() {
    assertNotNull(entityManager());
  }

  @Test
  public void contextUpWithTransaction() {
    withTransaction(() -> {});
  }

}
