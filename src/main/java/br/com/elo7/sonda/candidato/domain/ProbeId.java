package br.com.elo7.sonda.candidato.domain;

public class ProbeId {
  private final int value;

  public ProbeId(int value) {

    if (value <= 0)

      //TODO change message
      throw new IllegalStateException();

    this.value = value;
  }

  public int get() {
    return value;
  }
}
