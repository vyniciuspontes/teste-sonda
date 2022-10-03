package br.com.elo7.sonda.candidato.application;

import br.com.elo7.sonda.candidato.domain.Launch;
import br.com.elo7.sonda.candidato.domain.Planet;
import br.com.elo7.sonda.candidato.domain.PlanetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProbesService implements LandProbesUseCase {
  private final PlanetRepository planetRepository;

  public ProbesService(PlanetRepository planetRepository) {
    this.planetRepository = planetRepository;
  }

  @Override
  public void landProbes(List<Launch> launches) {
    final Set<Planet> successfullLaunches = launches.stream().map(launch -> {
      final Planet planet = planetRepository.findById(launch.getPlanetId()).stream().findFirst().orElseThrow();
      planet.land(launch.getProbe());
      planet.move(launch.getProbe(), launch.getCommands());
      return planet;
    }).collect(Collectors.toSet());

    planetRepository.saveAll(successfullLaunches);
  }
}
