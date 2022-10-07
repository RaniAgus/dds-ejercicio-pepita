package ar.edu.utn.frba.dds.pepita;

import static spark.Spark.after;
import static spark.Spark.exception;
import static spark.Spark.path;
import static spark.Spark.port;

import ar.edu.utn.frba.dds.pepita.controllers.ErrorController;
import ar.edu.utn.frba.dds.pepita.controllers.GolondrinaController;
import ar.edu.utn.frba.dds.pepita.exceptions.BadRequestException;
import ar.edu.utn.frba.dds.pepita.exceptions.NotFoundException;
import ar.edu.utn.frba.dds.pepita.golondrina.GolondrinaRepository;
import com.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

public class Ejemplo extends ApplicationContext implements WithSimplePersistenceUnit {
  public static void main(String[] args) {
    GolondrinaRepository golondrinaRepository = new GolondrinaRepository();
    GolondrinaController golondrinaController = new GolondrinaController(golondrinaRepository);
    ErrorController errorController = new ErrorController();

    port(getPort());
    path("/golondrinas", golondrinaController::getRoutes);
    exception(NotFoundException.class, errorController::handleNotFound);
    exception(BadRequestException.class, errorController::handleBadRequest);
    exception(Exception.class, errorController::handleInternalServerError);
    after((req, res) -> WithSimplePersistenceUnit.dispose());
  }
}
