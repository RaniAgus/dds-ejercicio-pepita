package ar.edu.utn.frba.dds.pepita.exceptions;

public class NotFoundException extends RuntimeException {
  public NotFoundException(Class<?> clazz, Object id) {
    super(clazz.getSimpleName() + " not found for id " + id.toString());
  }
}
