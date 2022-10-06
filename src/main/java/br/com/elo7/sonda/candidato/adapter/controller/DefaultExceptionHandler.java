package br.com.elo7.sonda.candidato.adapter.controller;

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
  private ResponseEntity<ErrorResponse> getDefaultResponseMessage(MethodArgumentNotValidException methodArgumentNotValidException)  {

    List<ErrorResponse.ErrorMessage> errorMessages = methodArgumentNotValidException.getBindingResult().getFieldErrors().stream().map(error ->
      new ErrorResponse.ErrorMessage(error.getField(), error.getDefaultMessage())).collect(Collectors.toList());

    return new ResponseEntity<>(new ErrorResponse(errorMessages), HttpStatus.BAD_REQUEST);
  }
}
