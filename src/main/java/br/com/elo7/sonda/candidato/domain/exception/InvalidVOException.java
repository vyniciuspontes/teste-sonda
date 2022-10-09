package br.com.elo7.sonda.candidato.domain.exception;

public class InvalidVOException extends DomainException{

  private static final String MESSAGE = "Invalid value object";

  public InvalidVOException(String className) {
    super(className, MESSAGE);
  }
}
