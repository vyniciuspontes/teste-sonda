package br.com.elo7.sonda.candidato.application;

import br.com.elo7.sonda.candidato.application.commands.CreatePlanet;
import br.com.elo7.sonda.candidato.application.commands.LandProbes;
import br.com.elo7.sonda.candidato.application.commands.MoveProbe;
import br.com.elo7.sonda.candidato.domain.Planet;
import br.com.elo7.sonda.candidato.domain.PlanetId;
import br.com.elo7.sonda.candidato.domain.PlanetRepository;
import br.com.elo7.sonda.candidato.domain.Probe;
import br.com.elo7.sonda.candidato.domain.ProbeId;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class PlanetService implements ControlProbesUseCase, ManagePlanetsUseCase {
  private final PlanetRepository planetRepository;

  public PlanetService(PlanetRepository planetRepository) {
    this.planetRepository = planetRepository;
  }

  @Override
  public Planet execute(LandProbes landProbes) {

    final Planet planet = planetRepository.findById(landProbes.planetId()).stream().findFirst()
      //TOdo change exception
      .orElseThrow(IllegalStateException::new);

    landProbes.coordinates()
      .forEach(coordinates -> {
          Probe newProbe = new Probe(new ProbeId(UUID.randomUUID().toString()), coordinates.probeName(),
            coordinates.startPosition(), coordinates.startDirection(), landProbes.planetId());
          planet.land(newProbe);
      });

    return planetRepository.save(planet);
  }

  @Override
  public Planet execute(MoveProbe moveProbe) {
    final Planet planet = planetRepository.findById(moveProbe.planetId())
      //Todo change exception
      .orElseThrow(IllegalStateException::new);

    planet.move(moveProbe.probeId(), moveProbe.commands());
    return planetRepository.save(planet);
  }

  @Override
  public Planet create(CreatePlanet createPlanet) {
    Planet newPlanet = new Planet(new PlanetId(UUID.randomUUID().toString()), createPlanet.planetName(),
      createPlanet.width(), createPlanet.height());

    return planetRepository.save(newPlanet);
  }

  @Override
  public Planet get(PlanetId planetId) {
    return planetRepository.findById(planetId)
      //TODO change exception
      .orElseThrow(() -> new IllegalStateException());
  }

  @Override
  public Set<Planet> getAll() {
    return planetRepository.findAll();
  }
}
