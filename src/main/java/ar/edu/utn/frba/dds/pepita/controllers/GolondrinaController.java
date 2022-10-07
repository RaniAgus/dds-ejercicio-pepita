package ar.edu.utn.frba.dds.pepita.controllers;

import static spark.Spark.delete;
import static spark.Spark.get;
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

  public void getRoutes() {
    get("", this::getGolondrinas, gson::toJson);
    post("", this::postGolondrina, gson::toJson);
    get("/:id", this::getGolondrinaById, gson::toJson);
    put("/:id", this::updateGolondrina, gson::toJson);
    delete("/:id", this::deleteGolondrina, gson::toJson);
  }

  private List<Golondrina> getGolondrinas(Request req, Response res) {
    return json(res, golondrinaRepository.findAll());
  }

  private Object getGolondrinaById(Request req, Response res) {
    Long id = parseId(req.params(":id"));

    return json(res, golondrinaRepository.findById(id));
  }

  private Golondrina postGolondrina(Request req, Response res) {
    Golondrina golondrina = parseBody(req.body(), Golondrina.class);

    beginTransaction();
    golondrina = golondrinaRepository.create(golondrina);
    commitTransaction();

    return json(res, golondrina);
  }

  private Golondrina updateGolondrina(Request req, Response res) {
    Long id = parseId(req.params(":id"));
    Golondrina golondrina = parseBody(req.body(), Golondrina.class);

    beginTransaction();
    golondrina = golondrinaRepository.update(id, golondrina);
    commitTransaction();

    return json(res, golondrina);
  }

  private Object deleteGolondrina(Request req, Response res) {
    Long id = parseId(req.params(":id"));

    beginTransaction();
    golondrinaRepository.delete(id);
    commitTransaction();

    return json(res, 204, null);
  }

}
