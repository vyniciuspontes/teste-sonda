package br.com.elo7.sonda.candidato.adapter.persistence;

import br.com.elo7.sonda.candidato.domain.Planet;
import br.com.elo7.sonda.candidato.domain.PlanetDimension;
import br.com.elo7.sonda.candidato.domain.PlanetId;
import br.com.elo7.sonda.candidato.domain.PlanetName;
import br.com.elo7.sonda.candidato.domain.Probe;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "planet")
public class PlanetDocument {

  @Id
  private PlanetId id;
  private final PlanetName name;
  private final PlanetDimension width;
  private final PlanetDimension height;
  private final List<Probe> probes;

  public PlanetDocument(PlanetId id, PlanetName name, PlanetDimension width, PlanetDimension height, List<Probe> probes) {
    this.id = id;
    this.name = name;
    this.width = width;
    this.height = height;
    this.probes = probes;
  }

  public static PlanetDocument from(Planet.Memento memento){
    return new PlanetDocument(memento.getId(), memento.getName(), memento.getWidth(), memento.getHeight(), memento.getProbes());
  }

  public Planet.Memento toMemento(){
    return new Planet.Memento(this.id, this.name, this.width, this.height, this.probes);
  }
}
