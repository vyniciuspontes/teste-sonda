package br.com.elo7.sonda.candidato.adapter.controller.validation;

import java.util.List;

public record ValidationErrorResponse(
  List<ValidationErrorMessage> errors
) {
  public record ValidationErrorMessage(
    String parameterName,
    String error
  ){}
}
