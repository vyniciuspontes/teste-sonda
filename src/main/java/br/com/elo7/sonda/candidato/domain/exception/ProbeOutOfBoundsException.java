package br.com.elo7.sonda.candidato.domain.exception;

import br.com.elo7.sonda.candidato.domain.Planet;
import br.com.elo7.sonda.candidato.domain.Probe;

public class ProbeOutOfBoundsException extends DomainException{
  public ProbeOutOfBoundsException(Probe probe) {
    super(Planet.class.getSimpleName(), String.format("Probe '%s' is out of bounds", probe.getName().value()));
  }
}
