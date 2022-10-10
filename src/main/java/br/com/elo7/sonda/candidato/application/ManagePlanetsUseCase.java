package br.com.elo7.sonda.candidato.application;

import br.com.elo7.sonda.candidato.application.commands.CreatePlanet;
import br.com.elo7.sonda.candidato.domain.Planet;
import br.com.elo7.sonda.candidato.domain.PlanetId;

import java.util.List;

public interface ManagePlanetsUseCase {

  Planet create(CreatePlanet createPlanet);

  Planet get(PlanetId planetId);

  List<Planet> getAll();
}
