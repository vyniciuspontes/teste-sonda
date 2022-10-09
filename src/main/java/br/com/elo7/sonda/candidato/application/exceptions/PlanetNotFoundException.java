package br.com.elo7.sonda.candidato.application.exceptions;

import br.com.elo7.sonda.candidato.domain.PlanetId;

public class PlanetNotFoundException extends ApplicationException{
  private static final String MESSAGE = "Planet not found: %s";

  public PlanetNotFoundException(PlanetId planetId) {
    super(String.format(MESSAGE, planetId.value()));
  }
}
