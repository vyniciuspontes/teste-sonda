package br.com.elo7.sonda.candidato.domain;

import java.util.List;

public class Launch {

  private final Probe probe;

  private final PlanetId planetId;

  private final List<Command> commands;

  public Launch(Probe probe, PlanetId planetId, List<Command> commands) {
    this.probe = probe;
    this.planetId = planetId;
    this.commands = commands;
  }

  public Probe getProbe() {
    return probe;
  }

  public PlanetId getPlanetId() {
    return planetId;
  }

  public List<Command> getCommands() {
    return commands;
  }
}
