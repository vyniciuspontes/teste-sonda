package br.com.elo7.sonda.candidato.adapter.persistence;

import br.com.elo7.sonda.candidato.domain.Planet;
import br.com.elo7.sonda.candidato.domain.PlanetId;
import br.com.elo7.sonda.candidato.domain.PlanetRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MementoPlanetRepository implements PlanetRepository {
  private final MementoPlanetRepositoryMongo planetRepositoryMongo;

  public MementoPlanetRepository(MementoPlanetRepositoryMongo planetRepositoryMongo) {
    this.planetRepositoryMongo = planetRepositoryMongo;
  }

  @Override
  public Planet save(Planet planet) {

    final Planet.Memento memento = new Planet.Memento(planet);
    final PlanetDocument planetDocument = PlanetDocument.from(memento);
    planetRepositoryMongo.save(planetDocument);

    return planet;
  }

  @Override
  public Optional<Planet> findById(PlanetId id) {

    return this.planetRepositoryMongo.findById(id).map(PlanetDocument::toMemento).map(Planet::from);
  }

  @Override
  public List<Planet> findAll() {
    return this.planetRepositoryMongo.findAll().stream().map(PlanetDocument::toMemento).map(Planet::from).toList();
  }
}
