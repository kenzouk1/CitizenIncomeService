package com.kian.service;

import com.kian.model.Frequency;
import com.kian.model.RegularAmount;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class WeeklyAmountValidator implements ConstraintValidator<WeeklyAmountConstraint, RegularAmount> {
    @Override
    public void initialize(WeeklyAmountConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(RegularAmount value, ConstraintValidatorContext context) {
        final Frequency frequency = value.getFrequency();
        final String amount = value.getAmount();
        if (toValidate(frequency, amount)) {
            double weeklyAmountInPound;
            try {
                weeklyAmountInPound = Double.parseDouble(amount) / frequency.getNumberOfWeeks();
            } catch (final NumberFormatException e) {
                // Skip validation if amount is not a number
                return true;
            }
            if (!isWholeNumber(weeklyAmountInPound * 100)) {
                return false;
            }
        }
        return true;
    }

    /* Perform validation check only if:
           1. Amount is not blank
           2. Frequency is TWO-WEEK, FOUR-WEEK, QUARTER or YEAR */
    private boolean toValidate(final Frequency frequency, final String amount) {
        return StringUtils.isNotBlank(amount)
                && frequency != null
                && Frequency.getMultiWeekFrequencies().contains(frequency);
    }

    private boolean isWholeNumber(final double value) {
        return value % 1 == 0;
    }
}
