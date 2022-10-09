package br.com.elo7.sonda.candidato.adapter.controller;

import br.com.elo7.sonda.candidato.application.exceptions.PlanetNotFoundException;
import br.com.elo7.sonda.candidato.domain.exception.DomainException;
import br.com.elo7.sonda.candidato.domain.exception.ProbeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class DefaultExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  private ResponseEntity<ValidationErrorResponse> getDefaultResponseMessage(MethodArgumentNotValidException methodArgumentNotValidException)  {

    List<ValidationErrorResponse.ValidationErrorMessage> errorMessages = methodArgumentNotValidException.getBindingResult().getFieldErrors().stream().map(error ->
      new ValidationErrorResponse.ValidationErrorMessage(error.getField(), error.getDefaultMessage())).collect(Collectors.toList());

    return new ResponseEntity<>(new ValidationErrorResponse(errorMessages), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(DomainException.class)
  private ResponseEntity<DefaultErrorResponse> getDefaultResponseMessage(DomainException domainException)  {

    DefaultErrorResponse response = new DefaultErrorResponse(domainException.getMessage());

    return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
  }

  @ExceptionHandler(ProbeNotFoundException.class)
  private ResponseEntity<DefaultErrorResponse> getDefaultResponseMessage(ProbeNotFoundException probeNotFoundException)  {

    DefaultErrorResponse response = new DefaultErrorResponse(probeNotFoundException.getMessage());

    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(PlanetNotFoundException.class)
  private ResponseEntity<DefaultErrorResponse> getDefaultResponseMessage(PlanetNotFoundException planetNotFoundException)  {

    DefaultErrorResponse response = new DefaultErrorResponse(planetNotFoundException.getMessage());

    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }
}
