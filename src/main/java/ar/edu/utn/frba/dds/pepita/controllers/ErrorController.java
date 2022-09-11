package ar.edu.utn.frba.dds.pepita.controllers;

import static spark.Spark.exception;

import ar.edu.utn.frba.dds.pepita.exceptions.BadRequestException;
import ar.edu.utn.frba.dds.pepita.exceptions.NotFoundException;
import spark.Request;
import spark.Response;

public class ErrorController implements Controller {

  public static void route() {
    ErrorController controller = new ErrorController();
    exception(NotFoundException.class, controller::handleNotFound);
    exception(BadRequestException.class, controller::handleBadRequest);
    exception(Exception.class, controller::handleInternalServerError);
  }

  private void handleNotFound(NotFoundException e, Request req, Response res) {
    res.status(404);
    res.type("application/json");
    res.body(gson.toJson(new Error("404 Not Found", e.getMessage())));
  }

  private void handleBadRequest(BadRequestException e, Request req, Response res) {
    res.status(400);
    res.type("application/json");
    res.body(gson.toJson(new Error("400 Bad Request", e.getMessage())));
  }

  private void handleInternalServerError(Exception e, Request req, Response res) {
    if (getTransaction().isActive()) {
      rollbackTransaction();
    }
    e.printStackTrace();
    res.status(500);
    res.type("application/json");
    res.body(gson.toJson(new Error("500 Internal Server Error", e.getMessage())));
  }

  private static class Error {
    private String error;
    private String message;

    public Error(String error, String message) {
      this.error = error;
      this.message = message;
    }

    public String getError() {
      return error;
    }

    public String getMessage() {
      return message;
    }
  }
}
