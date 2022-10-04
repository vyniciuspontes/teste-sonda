package br.com.elo7.sonda.candidato.domain;

public record PlanetId(String value) {

  public PlanetId {

    if (value == null || value.isEmpty() || value.isBlank())
      //TODO change message
      throw new IllegalArgumentException();

  }
}
