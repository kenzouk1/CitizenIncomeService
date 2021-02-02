package com.kian.exception;

public class InvalidWeeklyIncomeException extends RuntimeException {
    public InvalidWeeklyIncomeException(final String message) {
        super(message);
    }
}
