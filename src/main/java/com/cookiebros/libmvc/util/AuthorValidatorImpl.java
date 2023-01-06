package com.cookiebros.libmvc.util;

import jakarta.persistence.Column;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("AuthorValidator")
public class AuthorValidatorImpl implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
