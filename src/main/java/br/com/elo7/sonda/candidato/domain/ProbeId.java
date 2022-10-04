package br.com.elo7.sonda.candidato.domain;

public record ProbeId(String value) {
  public ProbeId {

    if (value == null || value.isEmpty() || value.isBlank())
      //TODO change message
      throw new IllegalArgumentException();

  }
}
