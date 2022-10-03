package br.com.elo7.sonda.candidato.domain;

import java.util.Objects;

public class Position {

  private final int x;

  private final int y;

  public Position(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
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

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }
}
