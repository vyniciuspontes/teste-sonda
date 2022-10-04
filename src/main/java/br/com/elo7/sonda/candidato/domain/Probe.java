package br.com.elo7.sonda.candidato.domain;

public class Probe {

  private final ProbeId id;
  private final PlanetId planetId;

  private final ProbeName name;
  private Position position;
  private Direction direction;

  public Probe(ProbeId id, ProbeName name, Position position, Direction direction, PlanetId planetId) {
    this.id = id;
    this.name = name;
    this.position = position;
    this.direction = direction;
    this.planetId = planetId;
  }


  public ProbeId getId() {
    return id;
  }

  public PlanetId getPlanetId() {
    return planetId;
  }

  public ProbeName getName() {
    return name;
  }

  public Position getPosition() {
    return position;
  }

  public Direction getDirection() {
    return this.direction;
  }

  public void applyCommandToProbe(Command command) {
    switch (command) {
      case R -> turnProbeRight();
      case L -> turnProbeLeft();
      case M -> moveProbeForward();
    }
  }

  private void moveProbeForward() {

    switch (direction) {
      case N -> this.position = this.position.increaseY();
      case W -> this.position = this.position.decreaseX();
      case S -> this.position = this.position.decreaseY();
      case E -> this.position = this.position.increaseX();
    }
  }

  private void turnProbeLeft() {

    switch (direction) {
      case N -> direction = Direction.W;
      case W -> direction = Direction.S;
      case S -> direction = Direction.E;
      case E -> direction = Direction.N;
    }
  }

  private void turnProbeRight() {

    switch (direction) {
      case N -> direction = Direction.E;
      case E -> direction = Direction.S;
      case S -> direction = Direction.W;
      case W -> direction = Direction.N;
    }
  }

  public Boolean isOverlapping(Probe comparisonProbe) {
    return this.position.equals(comparisonProbe.position);
  }


}
