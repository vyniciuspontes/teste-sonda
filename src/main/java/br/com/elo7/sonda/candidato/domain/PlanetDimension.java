package br.com.elo7.sonda.candidato.domain;

import br.com.elo7.sonda.candidato.domain.exception.InvalidVOException;

public record PlanetDimension(int value) {
  public PlanetDimension {

    if (value <= 0) {
      throw new InvalidVOException(PlanetDimension.class.getName());
    }

  }
}
