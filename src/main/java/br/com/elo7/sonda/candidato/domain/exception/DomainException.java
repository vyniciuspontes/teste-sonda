package br.com.elo7.sonda.candidato.domain.exception;

public class DomainException extends RuntimeException{
  private final String entity;
  private final String error;
  private static final String MESSAGE = "%s - %s";

  public DomainException(String entity, String error) {
    super(String.format(MESSAGE, entity, error));
    this.entity = entity;
    this.error = error;
  }
}
