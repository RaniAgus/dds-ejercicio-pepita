package ar.edu.utn.frba.dds.pepita;

import static spark.Spark.port;

import ar.edu.utn.frba.dds.pepita.controllers.ErrorController;
import ar.edu.utn.frba.dds.pepita.controllers.GolondrinaController;
import ar.edu.utn.frba.dds.pepita.golondrina.GolondrinaRepository;
import io.github.cdimascio.dotenv.Dotenv;
import org.uqbarproject.jpa.java8.extras.EntityManagerConfig;

public class Ejemplo {

  public static void main(String[] args) {
    Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

    EntityManagerConfig.setConnectionUrl(
        String.format("jdbc:postgresql://%s/%s",
          dotenv.get("POSTGRES_SERVICE"),
          dotenv.get("POSTGRES_DB")));
    EntityManagerConfig.setConnectionUsername(dotenv.get("POSTGRES_USER"));
    EntityManagerConfig.setConnectionPassword(dotenv.get("POSTGRES_PASSWORD"));

    port(Integer.parseInt(dotenv.get("PORT")));
    GolondrinaController.route(new GolondrinaRepository());
    ErrorController.route();
  }
}
