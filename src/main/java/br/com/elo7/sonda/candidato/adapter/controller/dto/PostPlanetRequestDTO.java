package br.com.elo7.sonda.candidato.adapter.controller.dto;

import br.com.elo7.sonda.candidato.application.commands.CreatePlanet;
import br.com.elo7.sonda.candidato.domain.PlanetDimension;
import br.com.elo7.sonda.candidato.domain.PlanetName;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public record PostPlanetRequestDTO(
  @NotNull
  String name,
  @NotNull
  @Min(1)
  Integer width,
  @NotNull
  @Min(1)
  Integer height) {

  public CreatePlanet toCommand(){
    return new CreatePlanet(new PlanetName(name), new PlanetDimension(width), new PlanetDimension(height));
  }
}
