package br.com.elo7.sonda.candidato.domain.repository;

import br.com.elo7.sonda.candidato.domain.Planet;
import br.com.elo7.sonda.candidato.domain.PlanetId;

import java.util.Optional;
import java.util.Set;

public interface PlanetRepository {

  void saveAll(Set<Planet> planet);

  Optional<Planet> findById(PlanetId id);

}
