package br.com.elo7.sonda.candidato.domain;

import br.com.elo7.sonda.candidato.domain.exception.InvalidVOException;

public record ProbeName(String value) {
  public ProbeName {

    if (value == null || value.isEmpty() || value.isBlank())
      throw new InvalidVOException(ProbeName.class.getName());
  }
}
