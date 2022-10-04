package br.com.elo7.sonda.candidato.domain;

import java.util.UUID;

public class ProbeGenerator {

  public static Probe gen(String planetId, int x, int y){
    return new Probe(new ProbeId(UUID.randomUUID().toString()), new Position(x,y), Direction.N, new PlanetId(planetId));
  }

  public static Probe gen(String planetId, int x, int y, Direction direction){
    return new Probe(new ProbeId(UUID.randomUUID().toString()), new Position(x,y), direction, new PlanetId(planetId));
  }
}
