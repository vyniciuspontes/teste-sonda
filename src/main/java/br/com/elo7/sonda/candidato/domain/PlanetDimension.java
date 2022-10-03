package br.com.elo7.sonda.candidato.domain;

public class PlanetDimension {
  private final int value;

  public PlanetDimension(int value) {

    if (value <= 0) {
      //TODO change message
      throw new IllegalArgumentException();
    }

    this.value = value;
  }

  public int get() {
    return value;
  }
}
