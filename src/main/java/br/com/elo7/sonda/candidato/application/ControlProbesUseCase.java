package br.com.elo7.sonda.candidato.application;

import br.com.elo7.sonda.candidato.application.commands.LandProbes;
import br.com.elo7.sonda.candidato.application.commands.MoveProbe;
import br.com.elo7.sonda.candidato.domain.Planet;

public interface ControlProbesUseCase {

  Planet execute(LandProbes landProbes);

  Planet execute(MoveProbe moveProbe);
}
