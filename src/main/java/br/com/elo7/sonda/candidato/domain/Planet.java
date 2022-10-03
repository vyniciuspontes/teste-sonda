package br.com.elo7.sonda.candidato.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Planet {
  private final PlanetId id;
  private final PlanetDimension width;
  private final PlanetDimension height;
  private final List<Probe> probes;

  public Planet(PlanetId id, PlanetDimension width, PlanetDimension height) {
    this.id = id;
    this.width = width;
    this.height = height;
    this.probes = new ArrayList<>();
  }

  @Override
  public int hashCode() {
    return this.id.get();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Planet) {
      return ((Planet) obj).id.get() == this.id.get();
    }
    return false;
  }

  public PlanetId getId() {
    return this.id;
  }

  public List<Probe> getProbes() {
    return probes;
  }

  public Optional<Probe> getProbe(ProbeId probeId) {
    return this.probes.stream().filter(probe -> probe.getId() == probeId).findFirst();
  }

  public void land(Probe newProbe) {

    if (isOverlaping(newProbe))
      //TODO change exception message
      throw new IllegalStateException();

    if (isOutOfBounds(newProbe))
      //TODO change exception message
      throw new IllegalStateException();

    probes.add(newProbe);
  }

  public void move(Probe probe, List<Command> commands) {
    final Probe currentProbe = probes.stream().filter(planetProbe -> planetProbe.equals(probe)).findFirst().orElseThrow();

    commands.forEach(command -> {
      currentProbe.applyCommandToProbe(command);

      if (command == Command.M) {
        if (isOverlaping(currentProbe))
          //TODO change exception message
          throw new IllegalStateException();

        if (isOutOfBounds(currentProbe))
          //TODO change exception message
          throw new IllegalStateException();
      }
    });
  }

  private Boolean isOverlaping(Probe probe) {
    return probes.stream()
      .filter(planetProbe -> planetProbe != probe)
      .anyMatch(planetProbe -> planetProbe.isOverlapping(probe));
  }

  private Boolean isOutOfBounds(Probe probe) {
    return probe.getPosition().getX() < 0 || probe.getPosition().getX() >= this.width.get() ||
      probe.getPosition().getY() < 0 || probe.getPosition().getY() >= this.height.get();
  }
}
