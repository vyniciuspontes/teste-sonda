package br.com.elo7.sonda.candidato.domain.exception;

public class InvalidVOException extends DomainException{

  public InvalidVOException(String className) {
    super(className, "Invalid %s");
  }
}
