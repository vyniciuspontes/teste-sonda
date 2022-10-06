package br.com.elo7.sonda.candidato.adapter.controller.dto;

import br.com.elo7.sonda.candidato.application.commands.LandProbes;
import br.com.elo7.sonda.candidato.domain.PlanetId;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

public record LandProbesRequestDTO(
  @NotEmpty
  List<@Valid ProbeRequestDTO> probes
) {

  public LandProbes toLandProbes(String planetId){
    return new LandProbes(new PlanetId(planetId), probes.stream().map(ProbeRequestDTO::toLandingProbe).toList());
  }

}
