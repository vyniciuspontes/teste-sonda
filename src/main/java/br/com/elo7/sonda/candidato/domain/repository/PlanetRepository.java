package br.com.elo7.sonda.candidato.domain.repository;

import br.com.elo7.sonda.candidato.domain.Planet;

import java.util.Optional;

public interface PlanetRepository {

	void save(Planet planet);

	Optional<Planet> findById(int id);

}
