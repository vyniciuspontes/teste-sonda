package br.com.elo7.sonda.candidato.domain;

import java.util.Optional;
import java.util.Set;

public interface PlanetRepository {

  void saveAll(Set<Planet> planet);

  Optional<Planet> findById(PlanetId id);

}
