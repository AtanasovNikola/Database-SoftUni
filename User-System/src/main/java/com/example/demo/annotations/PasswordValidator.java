package com.example.demo.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<Password, String> {

    private int minLength;
    private int maxLength;
    private boolean containsLowerCase;
    private boolean containsUpperCase;
    private boolean containsDigit;
    private boolean containsSpecialSymbol;

    @Override
    public void initialize(Password password) {
        this.minLength = password.minLength();
        this.maxLength = password.maxLength();
        this.containsLowerCase = password.containsLowerCase();
        this.containsUpperCase = password.containsUpperCase();
        this.containsDigit = password.containsDigit();
        this.containsSpecialSymbol = password.containsSpecialSymbol();
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null) {
            return true;
        }
        if (password.length() < minLength || password.length() > maxLength) {
            return false;
        }
        String pattern = "^";
        if (containsLowerCase) {
            pattern += "(?=.*[a-z])";
        }
        if (containsUpperCase) {
            pattern += "(?=.*[A-Z])";
        }
        if (containsDigit) {
            pattern += "(?=.*\\d)";
        }
        if (containsSpecialSymbol) {
            pattern += "(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?])";
        }
        pattern += ".+$";
        return Pattern.matches(pattern, password);
    }
}
