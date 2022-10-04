package br.com.elo7.sonda.candidato.domain;

public record ProbeName(String value) {
  public ProbeName {

    if (value == null || value.isEmpty() || value.isBlank())
      //TODO change message
      throw new IllegalArgumentException();
  }
}
