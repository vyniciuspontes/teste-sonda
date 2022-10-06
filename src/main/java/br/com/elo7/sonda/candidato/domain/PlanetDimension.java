package br.com.elo7.sonda.candidato.domain;

public record PlanetDimension(int value) {
  public PlanetDimension {

    if (value <= 0) {
      //TODO change message
      throw new IllegalArgumentException();
    }

  }
}
