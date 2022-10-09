package br.com.elo7.sonda.candidato.domain;

import br.com.elo7.sonda.candidato.domain.exception.InvalidVOException;

public record Position(int x, int y) {

  public Position {
    if(x < 0 || y < 0)
      throw new InvalidVOException(Position.class.getName());
  }

  public Position increaseX() {
    final int newX = this.x + 1;
    return new Position(newX, y);
  }

  public Position decreaseX() {
    final int newX = this.x - 1;
    return new Position(newX, y);
  }

  public Position increaseY() {
    final int newY = this.y + 1;
    return new Position(x, newY);
  }

  public Position decreaseY() {
    final int newY = this.y - 1;
    return new Position(x, newY);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Position position)) return false;
    return x == position.x && y == position.y;
  }

}
