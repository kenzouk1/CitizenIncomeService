package com.kian.service;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = WeeklyAmountValidator.class)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface WeeklyAmountConstraint {
    String message() default "Invalid payment amount. The weekly amount in Pence is not a whole number";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
