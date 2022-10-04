package br.com.elo7.sonda.candidato.domain;

import java.util.UUID;

public class PlanetGenerator {

  public static Planet gen(){
    return new Planet(new PlanetId(UUID.randomUUID().toString()), new PlanetName("Planet Name"), new PlanetDimension(5), new PlanetDimension(5));
  }

  public static Planet gen(PlanetId id){
    return new Planet(id, new PlanetName("Planet Name"), new PlanetDimension(5), new PlanetDimension(5));
  }
}
