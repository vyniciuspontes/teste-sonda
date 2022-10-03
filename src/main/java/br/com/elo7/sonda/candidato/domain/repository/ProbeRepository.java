package br.com.elo7.sonda.candidato.domain.repository;

import br.com.elo7.sonda.candidato.domain.Probe;

import java.util.Optional;

public interface ProbeRepository {

	void save(Probe probe);

	Optional<Probe> findById(int id);

}
