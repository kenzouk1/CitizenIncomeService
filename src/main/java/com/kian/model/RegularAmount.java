package com.kian.model;

import com.kian.service.WeeklyAmountConstraint;

@WeeklyAmountConstraint
public class RegularAmount {
    private Frequency frequency;
    private String amount;

    public RegularAmount() {
    }

    public RegularAmount(Frequency frequency, String amount) {
        this.frequency = frequency;
        this.amount = amount;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
