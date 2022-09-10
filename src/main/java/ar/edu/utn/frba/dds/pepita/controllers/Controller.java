package ar.edu.utn.frba.dds.pepita.controllers;

import ar.edu.utn.frba.dds.pepita.exceptions.BadRequestException;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

public interface Controller extends WithGlobalEntityManager, TransactionalOps {
  Gson gson = new Gson();

  default Long parseId(String s) {
    try {
      return Long.parseLong(s);
    } catch (NumberFormatException e) {
      throw new BadRequestException("invalid :id");
    }
  }

  default <T> T parseBody(String body, Class<T> clazz) {
    try {
      return gson.fromJson(body, clazz);
    } catch (JsonSyntaxException e) {
      throw new BadRequestException("invalid body");
    }
  }
}
