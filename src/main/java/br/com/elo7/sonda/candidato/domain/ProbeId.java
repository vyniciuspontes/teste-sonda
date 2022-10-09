package br.com.elo7.sonda.candidato.domain;

import br.com.elo7.sonda.candidato.domain.exception.InvalidVOException;

public record ProbeId(String value) {
  public ProbeId {

    if (value == null || value.isEmpty() || value.isBlank())
      throw new InvalidVOException(ProbeId.class.getName());

  }
}
