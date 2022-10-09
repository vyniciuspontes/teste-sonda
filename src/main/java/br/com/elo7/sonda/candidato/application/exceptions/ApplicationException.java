package br.com.elo7.sonda.candidato.application.exceptions;

abstract class ApplicationException extends RuntimeException{
  public ApplicationException(String message) {
    super(message);
  }
}
