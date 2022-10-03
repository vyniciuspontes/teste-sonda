package br.com.elo7.sonda.candidato.application;

import br.com.elo7.sonda.candidato.domain.Launch;
import br.com.elo7.sonda.candidato.domain.Planet;
import br.com.elo7.sonda.candidato.domain.Probe;
import br.com.elo7.sonda.candidato.domain.repository.PlanetRepository;
import br.com.elo7.sonda.candidato.domain.repository.ProbeRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProbesService implements LandProbesUseCase {
  private final PlanetRepository planetRepository;

  private final ProbeRepository probeRepository;

  public ProbesService(PlanetRepository planetRepository, ProbeRepository probeRepository) {
    this.planetRepository = planetRepository;
    this.probeRepository = probeRepository;
  }

  @Override
  public void landProbes(List<Launch> launches) {
    final Map<Planet, List<Probe>> successfullLaunches = launches.stream().map(launch -> {
      final Planet planet = planetRepository.findById(launch.getPlanetId()).stream().findFirst().orElseThrow();
      planet.land(launch.getProbe());
      planet.move(launch.getProbe(), launch.getCommands());
      return planet;
    }).collect(Collectors.toMap(planet -> planet, Planet::getProbes));

    planetRepository.saveAll(successfullLaunches.keySet());
    probeRepository.saveAll(successfullLaunches.values().stream()
      .flatMap(Collection::stream).collect(Collectors.toSet()));
  }
}
