package br.com.elo7.sonda.candidato.domain.repository;

import br.com.elo7.sonda.candidato.domain.Probe;
import br.com.elo7.sonda.candidato.domain.ProbeId;

import java.util.Optional;
import java.util.Set;

public interface ProbeRepository {

  void saveAll(Set<Probe> newProbes);

  Optional<Probe> findById(ProbeId id);

}
