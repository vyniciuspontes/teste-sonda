package br.com.elo7.sonda.candidato.application;

import br.com.elo7.sonda.candidato.application.commands.CreatePlanet;
import br.com.elo7.sonda.candidato.domain.Planet;
import br.com.elo7.sonda.candidato.domain.PlanetId;

import java.util.Set;

public interface ManagePlanetsUseCase {

  Planet create(CreatePlanet createPlanet);

  Planet get(PlanetId planetId);

  Set<Planet> getAll();
}
