package br.com.elo7.sonda.candidato.adapter.controller.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ListOfEnumValidator.class)
public @interface ListOfEnum {
  Class<? extends Enum<?>> enumClass();
  String message() default "must be any of enum {enumClass}";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}
