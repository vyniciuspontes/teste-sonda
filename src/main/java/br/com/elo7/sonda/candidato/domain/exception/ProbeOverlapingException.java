package br.com.elo7.sonda.candidato.domain.exception;

import br.com.elo7.sonda.candidato.domain.Planet;
import br.com.elo7.sonda.candidato.domain.Probe;

public class ProbeOverlapingException extends DomainException{
  public ProbeOverlapingException(Probe probe) {
    super(Planet.class.getName(), String.format("Probe %s is overlaping", probe.getName().value()));
  }
}
