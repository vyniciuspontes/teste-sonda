package br.com.elo7.sonda.candidato.domain;

import br.com.elo7.sonda.candidato.domain.exception.InvalidVOException;

public record PlanetName(String value) {

  public PlanetName {

    if (value == null || value.isEmpty() || value.isBlank())
      throw new InvalidVOException(PlanetName.class.getSimpleName());

  }
}
