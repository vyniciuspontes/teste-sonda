package br.com.elo7.sonda.candidato.application.commands;

import br.com.elo7.sonda.candidato.domain.Direction;
import br.com.elo7.sonda.candidato.domain.PlanetId;
import br.com.elo7.sonda.candidato.domain.Position;
import br.com.elo7.sonda.candidato.domain.ProbeName;

import java.util.List;

public record LandProbes(PlanetId planetId, List<LandingProbe> coordinates) {
  public record LandingProbe(
    ProbeName probeName, Position startPosition, Direction startDirection
  ) {
  }
}


