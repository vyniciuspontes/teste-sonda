package br.com.elo7.sonda.candidato.domain;

import java.util.Optional;
import java.util.Set;

public interface PlanetRepository {

  Planet save(Planet planet);

  Optional<Planet> findById(PlanetId id);

  Set<Planet> findAll();

}
