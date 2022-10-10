package br.com.elo7.sonda.candidato.adapter.controller.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListOfEnumValidator implements ConstraintValidator<ListOfEnum, List<String>> {
  private List<String> acceptedValues;

  @Override
  public void initialize(ListOfEnum annotation) {
    acceptedValues = Stream.of(annotation.enumClass().getEnumConstants())
      .map(Enum::name)
      .collect(Collectors.toList());
  }

  @Override
  public boolean isValid(List<String> value, ConstraintValidatorContext context) {
    context.disableDefaultConstraintViolation();
    context.buildConstraintViolationWithTemplate("should be any value of: " + acceptedValues.toString()).addConstraintViolation();

    if (value == null || value.isEmpty()) {
      return true;
    }

    return new HashSet<>(acceptedValues).containsAll(value);
  }
}
