package com.ravensoftware.reporting.exception;

import org.springframework.http.HttpStatus;

import java.util.function.Supplier;

/**
 * Created by bilga
 */
public class ReportingApiException extends RuntimeException implements Supplier<ReportingApiException> {

    private final HttpStatus errorCode;


    public ReportingApiException(String message, HttpStatus errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    @Override
    public ReportingApiException get() {
        return this;
    }

    public HttpStatus getErrorCode() {
        return errorCode;
    }
}
