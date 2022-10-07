package ar.edu.utn.frba.dds.pepita;

import com.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.Arrays;
import java.util.Optional;

public class ApplicationContext {
  static {
    if (notNull("DB_URL", "DB_USERNAME", "DB_PASSWORD", "DB_DRIVER", "DB_DIALECT")) {
      WithSimplePersistenceUnit.configure(properties -> properties
          .set("hibernate.connection.url", System.getenv("DB_URL"))
          .set("hibernate.connection.username", System.getenv("DB_USERNAME"))
          .set("hibernate.connection.password", System.getenv("DB_PASSWORD"))
          .set("hibernate.connection.driver_class", System.getenv("DB_DRIVER"))
          .set("hibernate.dialect", System.getenv("DB_DIALECT"))
      );
    }
  }

  public static Integer getPort() {
    return Optional.ofNullable(System.getenv("PORT"))
        .map(Integer::parseInt)
        .orElse(8080);
  }

  private static boolean notNull(String... variables) {
    return Arrays.stream(variables).allMatch(s -> System.getenv().containsKey(s));
  }
}
