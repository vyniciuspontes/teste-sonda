package br.com.elo7.sonda.candidato.domain;

import br.com.elo7.sonda.candidato.domain.exception.InvalidVOException;

import java.util.Objects;

public record PlanetId(String value) {

  public PlanetId {

    if (value == null || value.isEmpty() || value.isBlank())
      throw new InvalidVOException(PlanetId.class.getName());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof PlanetId planetId)) return false;
    return value.equals(planetId.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}
