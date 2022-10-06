package br.com.elo7.sonda.candidato.adapter.controller.dto;

import br.com.elo7.sonda.candidato.application.commands.LandProbes;
import br.com.elo7.sonda.candidato.domain.Direction;
import br.com.elo7.sonda.candidato.domain.Position;
import br.com.elo7.sonda.candidato.domain.ProbeName;

public record ProbeRequestDTO(
  String name,
  int x,
  int y,
  String direction
) {

  public LandProbes.LandingProbe toLandingProbe(){
    return new LandProbes.LandingProbe(new ProbeName(this.name()), new Position(x, y), Direction.valueOf(direction));
  }

}
