package br.com.elo7.sonda.candidato.application;

import br.com.elo7.sonda.candidato.domain.Launch;

import java.util.List;

public interface LandProbesUseCase {

  void landProbes(List<Launch> launches);
}
