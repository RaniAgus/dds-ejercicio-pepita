package ar.edu.utn.frba.dds.pepita;

import com.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import io.github.cdimascio.dotenv.Dotenv;

import java.util.Optional;

public class ApplicationContext {
  private Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

  public void configureDatabase() {
    WithSimplePersistenceUnit.configure(properties -> properties
        .set("hibernate.connection.url", String.format("jdbc:postgresql://%s/%s",
            dotenv.get("POSTGRES_SERVICE"), dotenv.get("POSTGRES_DB")))
        .set("hibernate.connection.username", dotenv.get("POSTGRES_USER"))
        .set("hibernate.connection.password", dotenv.get("POSTGRES_PASSWORD"))
    );
  }

  public Integer getPort() {
    return Optional.ofNullable(dotenv.get("PORT"))
        .map(Integer::parseInt)
        .orElse(8080);
  }
}
