package br.com.elo7.sonda.candidato.domain;

public record PlanetName(String value) {

  public PlanetName {

    if (value == null || value.isEmpty() || value.isBlank())
      //TODO change message
      throw new IllegalArgumentException();

  }
}
