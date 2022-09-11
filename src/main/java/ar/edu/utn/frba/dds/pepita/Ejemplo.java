package ar.edu.utn.frba.dds.pepita;

import static spark.Spark.after;
import static spark.Spark.port;

import ar.edu.utn.frba.dds.pepita.controllers.ErrorController;
import ar.edu.utn.frba.dds.pepita.controllers.GolondrinaController;
import ar.edu.utn.frba.dds.pepita.golondrina.GolondrinaRepository;
import io.github.cdimascio.dotenv.Dotenv;
import org.uqbarproject.jpa.java8.extras.EntityManagerConfig;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

public class Ejemplo implements WithGlobalEntityManager {

  public static void main(String[] args) {
    Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

    new EntityManagerConfig()
        .setConnectionUrl(String.format("jdbc:postgresql://%s/%s",
            dotenv.get("POSTGRES_SERVICE"),
            dotenv.get("POSTGRES_DB")))
        .setConnectionUsername(dotenv.get("POSTGRES_USER"))
        .setConnectionPassword(dotenv.get("POSTGRES_PASSWORD"))
        .applyConfiguration();

    port(Integer.parseInt(dotenv.get("PORT")));
    GolondrinaController.route(new GolondrinaRepository());
    ErrorController.route();
    after((req, res) -> {
      if (PerThreadEntityManagers.getEntityManager().isOpen()) {
        PerThreadEntityManagers.closeEntityManager();
      }
    });
  }
}
