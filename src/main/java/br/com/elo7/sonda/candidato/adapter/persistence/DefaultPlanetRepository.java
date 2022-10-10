package br.com.elo7.sonda.candidato.adapter.persistence;

import br.com.elo7.sonda.candidato.domain.Planet;
import br.com.elo7.sonda.candidato.domain.PlanetId;
import br.com.elo7.sonda.candidato.domain.PlanetRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DefaultPlanetRepository implements PlanetRepository {
  private final PlanetRepositoryMongo planetRepositoryMongo;

  public DefaultPlanetRepository(PlanetRepositoryMongo planetRepositoryMongo) {
    this.planetRepositoryMongo = planetRepositoryMongo;
  }

  @Override
  public Planet save(Planet planet) {

    return this.planetRepositoryMongo.save(planet);
  }

  @Override
  public Optional<Planet> findById(PlanetId id) {
    return this.planetRepositoryMongo.findById(id);
  }

  @Override
  public List<Planet> findAll() {
    return this.planetRepositoryMongo.findAll();
  }
}
