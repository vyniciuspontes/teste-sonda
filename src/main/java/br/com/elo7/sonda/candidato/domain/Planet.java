package br.com.elo7.sonda.candidato.domain;

import br.com.elo7.sonda.candidato.domain.exception.ProbeNotFoundException;
import br.com.elo7.sonda.candidato.domain.exception.ProbeOutOfBoundsException;
import br.com.elo7.sonda.candidato.domain.exception.ProbeOverlapingException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Planet {
  private PlanetId id;
  private PlanetDimension width;
  private PlanetDimension height;
  private List<Probe> probes;

  private PlanetName name;

  //Usado para Spring Data
  private Planet() {}

  public Planet(PlanetId id, PlanetName name, PlanetDimension width, PlanetDimension height) {
    this.id = id;
    this.name = name;
    this.width = width;
    this.height = height;
    this.probes = new ArrayList<>();
  }

  public PlanetId getId() {
    return this.id;
  }

  public PlanetDimension getWidth() {
    return width;
  }

  public PlanetDimension getHeight() {
    return height;
  }

  public PlanetName getName() {
    return name;
  }

  public List<Probe> getProbes() {
    return probes;
  }

  public Optional<Probe> getProbe(ProbeId probeId) {
    return this.probes.stream().filter(probe -> probe.getId().equals(probeId)).findFirst();
  }

  public void land(Probe newProbe) {

    if (isOverlaping(newProbe))
      throw new ProbeOverlapingException(newProbe);

    if (isOutOfBounds(newProbe))
      throw new ProbeOutOfBoundsException(newProbe);

    probes.add(newProbe);
  }

  public void move(ProbeId probeId, List<Command> commands) {
    final Probe currentProbe = probes.stream().filter(planetProbe -> planetProbe.getId().equals(probeId)).findFirst()
      .orElseThrow(() -> new ProbeNotFoundException(probeId));

    commands.forEach(command -> {
      currentProbe.applyCommandToProbe(command);

      if (Command.M.equals(command)) {
        if (isOverlaping(currentProbe))
          throw new ProbeOverlapingException(currentProbe);

        if (isOutOfBounds(currentProbe))
          throw new ProbeOutOfBoundsException(currentProbe);
      }
    });
  }

  private Boolean isOverlaping(Probe probe) {
    return probes.stream()
      .filter(planetProbe -> planetProbe != probe)
      .anyMatch(planetProbe -> planetProbe.isOverlapping(probe));
  }

  private Boolean isOutOfBounds(Probe probe) {
    return probe.getPosition().x() < 0 || probe.getPosition().x() >= this.width.value() ||
      probe.getPosition().y() < 0 || probe.getPosition().y() >= this.height.value();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Planet planet)) return false;
    return id.equals(planet.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  public Memento createSnapshot(){
    return new Memento(this);
  }

  public static Planet  restore(Memento memento){
    final Planet planet = new Planet(memento.id, memento.name, memento.width, memento.height);
    memento.probes.forEach(planet::land);
    return planet;
  }

  public static class Memento {

    private final PlanetId id;
    private final PlanetName name;
    private final PlanetDimension width;
    private final PlanetDimension height;
    private final List<Probe> probes;

    public Memento(PlanetId id, PlanetName name, PlanetDimension width, PlanetDimension height, List<Probe> probes) {
      this.id = id;
      this.name = name;
      this.width = width;
      this.height = height;
      this.probes = probes;
    }

    Memento(Planet planet) {
      this.id = planet.id;
      this.name = planet.name;
      this.width = planet.width;
      this.height = planet.height;
      this.probes = planet.probes;
    }

    public PlanetId getId() {
      return id;
    }

    public PlanetName getName() {
      return name;
    }

    public PlanetDimension getWidth() {
      return width;
    }

    public PlanetDimension getHeight() {
      return height;
    }

    public List<Probe> getProbes() {
      return probes;
    }
  }
}
