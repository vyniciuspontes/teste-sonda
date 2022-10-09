package br.com.elo7.sonda.candidato.domain.exception;

import br.com.elo7.sonda.candidato.domain.Probe;
import br.com.elo7.sonda.candidato.domain.ProbeId;

public class ProbeNotFoundException extends DomainException {
  private static final String MESSAGE = "Probe not found '%s'";

  public ProbeNotFoundException(ProbeId probeId) {
    super(Probe.class.getSimpleName(), String.format(MESSAGE, probeId.value()));
  }
}
