package ar.edu.utn.frba.dds.pepita.controllers;

import ar.edu.utn.frba.dds.pepita.exceptions.BadRequestException;
import ar.edu.utn.frba.dds.pepita.exceptions.NotFoundException;
import spark.Request;
import spark.Response;

public class ErrorController implements Controller {

  public void handleNotFound(NotFoundException e, Request req, Response res) {
    json(res, 404, new Error("Not found", e.getMessage()));
  }

  public void handleBadRequest(BadRequestException e, Request req, Response res) {
    json(res, 400, new Error("Bad request", e.getMessage()));
  }

  public void handleInternalServerError(Exception e, Request req, Response res) {
    if (getTransaction().isActive()) {
      rollbackTransaction();
    }
    e.printStackTrace();
    json(res, 500, new Error("Internal server error", e.getMessage()));
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
