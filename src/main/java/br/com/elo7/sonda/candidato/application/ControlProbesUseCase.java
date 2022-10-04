package br.com.elo7.sonda.candidato.application;

import br.com.elo7.sonda.candidato.application.commands.LandProbe;
import br.com.elo7.sonda.candidato.application.commands.MoveProbe;

public interface ControlProbesUseCase {

  void execute(LandProbe landProbe);

  void execute(MoveProbe moveProbe);
}
