package ar.edu.utn.frba.dds.pepita.controllers;

import static spark.Spark.after;
import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.path;
import static spark.Spark.post;
import static spark.Spark.put;

import ar.edu.utn.frba.dds.pepita.golondrina.Golondrina;
import ar.edu.utn.frba.dds.pepita.golondrina.GolondrinaRepository;
import java.util.List;
import spark.Request;
import spark.Response;

public class GolondrinaController implements Controller {
  private final GolondrinaRepository golondrinaRepository;

  public GolondrinaController(GolondrinaRepository golondrinaRepository) {
    this.golondrinaRepository = golondrinaRepository;
  }

  public static void route(GolondrinaRepository golondrinaRepository) {
    GolondrinaController controller = new GolondrinaController(golondrinaRepository);
    path("/golondrinas", () -> {
      get("", controller::getGolondrinas, gson::toJson);
      post("", controller::postGolondrina, gson::toJson);
      get("/:id", controller::getGolondrinaById, gson::toJson);
      put("/:id", controller::updateGolondrina, gson::toJson);
      delete("/:id", controller::deleteGolondrina, gson::toJson);
    });
  }

  private List<Golondrina> getGolondrinas(Request req, Response res) {
    res.type("application/json");
    return golondrinaRepository.findAll();
  }

  private Object getGolondrinaById(Request req, Response res) {
    Long id = parseId(req.params(":id"));

    res.type("application/json");
    return golondrinaRepository.findById(id);
  }

  private Golondrina postGolondrina(Request req, Response res) {
    Golondrina golondrina = parseBody(req.body(), Golondrina.class);

    beginTransaction();
    golondrina = golondrinaRepository.create(golondrina);
    commitTransaction();

    res.type("application/json");
    return golondrina;
  }

  private Golondrina updateGolondrina(Request req, Response res) {
    Long id = parseId(req.params(":id"));
    Golondrina golondrina = parseBody(req.body(), Golondrina.class);

    beginTransaction();
    golondrina = golondrinaRepository.update(id, golondrina);
    commitTransaction();

    res.type("application/json");
    return golondrina;
  }

  private Object deleteGolondrina(Request req, Response res) {
    Long id = parseId(req.params(":id"));

    beginTransaction();
    golondrinaRepository.delete(id);
    commitTransaction();

    res.type("application/json");
    res.status(204);
    return null;
  }

}
