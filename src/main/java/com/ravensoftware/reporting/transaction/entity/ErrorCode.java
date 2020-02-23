package com.ravensoftware.reporting.transaction.entity;

/**
 * Created by bilga on 23-02-2020
 */
public enum ErrorCode {

    DNH("Do not honor"),
    INVALID_TRANSACTION("Invalid transaction"),
    INVALID_CARD("Invalid Card"),
    INCORRECT_PIN("Incorrect PIN"),
    CURRENCY_NOT_ALLOWED("Currency not allowed");

    private String label;

    ErrorCode(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
