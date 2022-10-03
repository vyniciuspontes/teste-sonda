package br.com.elo7.sonda.candidato.adapter.persistence;

import br.com.elo7.sonda.candidato.domain.Planet;
import br.com.elo7.sonda.candidato.domain.PlanetId;
import br.com.elo7.sonda.candidato.domain.Probe;
import br.com.elo7.sonda.candidato.domain.ProbeId;
import br.com.elo7.sonda.candidato.domain.repository.PlanetRepository;
import br.com.elo7.sonda.candidato.domain.repository.ProbeRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Component
class InMemoryDatabase {
  private static final Map<Planet, List<Probe>> probesPerPlanet = new HashMap<>();

  @Repository
  public class PlanetDAO implements PlanetRepository {
    public void saveAll(Set<Planet> planets) {
      planets.forEach(planet -> probesPerPlanet.put(planet, planet.getProbes()));
    }

    public Optional<Planet> findById(PlanetId id) {
      return probesPerPlanet.keySet()
        .stream()
        .filter(planet -> planet.getId() == id)
        .findFirst();
    }
  }

  @Repository
  public class ProbeDAO implements ProbeRepository {
    @Override
    public void saveAll(Set<Probe> newProbes) {
      newProbes.forEach(probe -> {
        final Planet foundPlanet = probesPerPlanet.keySet().stream().filter(planet -> planet.getId() == probe.getPlanetId()).findFirst().orElseThrow();
        List<Probe> probes = probesPerPlanet.get(foundPlanet);
        probes.add(probe);
      });
    }

    @Override
    public Optional<Probe> findById(ProbeId id) {
      return probesPerPlanet.entrySet().stream().flatMap(
        entry -> entry.getValue()
          .stream()
          .filter(probe -> probe.getId() == id)
      ).findFirst();
    }
  }
}
