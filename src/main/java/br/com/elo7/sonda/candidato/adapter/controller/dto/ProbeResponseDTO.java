package br.com.elo7.sonda.candidato.adapter.controller.dto;

import br.com.elo7.sonda.candidato.application.commands.LandProbes;
import br.com.elo7.sonda.candidato.domain.Direction;
import br.com.elo7.sonda.candidato.domain.Position;
import br.com.elo7.sonda.candidato.domain.Probe;
import br.com.elo7.sonda.candidato.domain.ProbeName;

public record ProbeResponseDTO(
  String id,
  String name,
  int x,
  int y,
  String direction
) {

  public static ProbeResponseDTO fromProbe(Probe probe) {
    return new ProbeResponseDTO(probe.getId().value(), probe.getName().value(), probe.getPosition().x(),
      probe.getPosition().y(), probe.getDirection().name());
  }

}
