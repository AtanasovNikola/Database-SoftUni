package com.example.demo.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target( ElementType.FIELD )
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {
    String message() default "Invalid password";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    int minLength();
    int maxLength();
    boolean containsDigit() default false;
    boolean containsLowerCase() default false;
    boolean containsUpperCase() default false;
    boolean containsSpecialSymbol() default false;
}
