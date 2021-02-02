package com.kian.service;


import com.kian.exception.InvalidWeeklyIncomeException;
import com.kian.model.RegularAmount;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class IncomeValidator {
    private static final ValidatorFactory FACTORY = Validation.buildDefaultValidatorFactory();
    private static final Validator VALIDATOR = FACTORY.getValidator();

    public void validate(final RegularAmount regularAmount) {
        Set<ConstraintViolation<RegularAmount>> violations = VALIDATOR.validate(regularAmount);
        if (violations.size() > 0) {
            throw new InvalidWeeklyIncomeException(violations.iterator().next().getMessage());
        }
    }

}
