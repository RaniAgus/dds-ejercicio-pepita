package ar.edu.utn.frba.dds.pepita.controllers;

import ar.edu.utn.frba.dds.pepita.exceptions.BadRequestException;
import com.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import spark.Response;

public interface Controller extends WithSimplePersistenceUnit {
  Gson gson = new GsonBuilder()
      .setPrettyPrinting()
      .serializeNulls()
      .create();

  default Long parseId(String s) {
    try {
      return Long.parseLong(s);
    } catch (NumberFormatException e) {
      throw new BadRequestException("Invalid id");
    }
  }

  default <T> T parseBody(String body, Class<T> clazz) {
    try {
      return gson.fromJson(body, clazz);
    } catch (JsonSyntaxException e) {
      throw new BadRequestException("Invalid body");
    }
  }

  default <T> T json(Response res, int status, T body) {
    res.status(status);
    res.type("application/json");
    res.body(gson.toJson(body));
    return body;
  }

  default <T> T json(Response res, T body) {
    res.type("application/json");
    return body;
  }
}
