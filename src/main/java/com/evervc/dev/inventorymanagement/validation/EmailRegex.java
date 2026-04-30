package com.evervc.dev.inventorymanagement.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = EmailRegexValidation.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailRegex {

    String message() default "El formato del correo no es válido.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
