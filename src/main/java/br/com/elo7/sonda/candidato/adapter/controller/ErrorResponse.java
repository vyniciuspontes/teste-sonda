package br.com.elo7.sonda.candidato.adapter.controller;

import java.util.List;

public record ErrorResponse(
  List<ErrorMessage> errorMessages
) {
  public record ErrorMessage(
    String parameterName,
    String description
  ) {
  }
}
