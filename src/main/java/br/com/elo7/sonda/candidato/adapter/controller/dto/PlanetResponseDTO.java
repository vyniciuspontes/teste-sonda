package br.com.elo7.sonda.candidato.adapter.controller.dto;

import br.com.elo7.sonda.candidato.domain.Planet;

import java.util.List;

public record PlanetResponseDTO(
  String id,
  String name,
  Integer width,
  Integer height,
  List<ProbeResponseDTO> probes
  ) {

  public static PlanetResponseDTO from(Planet planet) {

    List<ProbeResponseDTO> probes = planet.getProbes().stream().map(ProbeResponseDTO::fromProbe).toList();

    return new PlanetResponseDTO(planet.getId().value(), planet.getName().value(),
      planet.getWidth().value(), planet.getHeight().value(), probes);
  }
}
