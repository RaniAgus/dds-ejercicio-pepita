package ar.edu.utn.frba.dds.pepita;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Golondrina {
  @Id @GeneratedValue
  private Long id;

  private Integer energia;

  private Golondrina() {
  }

  public Golondrina(int energia) {
    this.energia = energia;
  }

  public void volar() {
    energia -= 10;
  }

  public void comer(int alpiste) {
    energia += alpiste * 3;
  }

  public Integer getEnergia() {
    return energia;
  }

  @Override
  public String toString() {
    return "Golondrina{" +
        "energia=" + energia +
        '}';
  }
}
