package br.com.elo7.sonda.candidato.adapter.persistence;

import br.com.elo7.sonda.candidato.domain.Planet;
import br.com.elo7.sonda.candidato.domain.PlanetId;
import br.com.elo7.sonda.candidato.domain.PlanetRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
class InMemoryDatabase {
  private static final Set<Planet> planets = new HashSet<>();

  @Repository
  public class PlanetDAO implements PlanetRepository {
    public Planet save(Planet planet) {
      InMemoryDatabase.planets.add(planet);
      return planet;
    }

    public Optional<Planet> findById(PlanetId id) {
      return planets
        .stream()
        .filter(planet -> planet.getId().equals(id))
        .findFirst();
    }

    @Override
    public Set<Planet> findAll() {
      return planets;
    }
  }
}
