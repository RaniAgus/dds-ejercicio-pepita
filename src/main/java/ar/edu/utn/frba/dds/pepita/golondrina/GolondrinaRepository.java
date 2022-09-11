package ar.edu.utn.frba.dds.pepita.golondrina;

import ar.edu.utn.frba.dds.pepita.exceptions.NotFoundException;
import java.util.List;
import java.util.Optional;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

public class GolondrinaRepository implements WithGlobalEntityManager, EntityManagerOps {
  public List<Golondrina> findAll() {
    return createQuery("from Golondrina", Golondrina.class)
        .getResultList();
  }

  public Golondrina findById(Long id) {
    return Optional.ofNullable(find(Golondrina.class, id))
        .orElseThrow(() -> new NotFoundException(Golondrina.class, id));
  }

  public Golondrina create(Golondrina golondrina) {
    Golondrina aux = new Golondrina(golondrina.getEnergia());
    persist(aux);
    return aux;
  }

  public Golondrina update(Long id, Golondrina golondrina) {
    Golondrina aux = findById(id);
    aux.setEnergia(golondrina.getEnergia());
    return merge(aux);
  }

  public void delete(Long id) {
    remove(findById(id));
  }
}
