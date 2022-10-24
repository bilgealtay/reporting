package com.ravensoftware.reporting.exception;

import com.ravensoftware.reporting.base.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

/**
 * Created by bilga
 */
@RestControllerAdvice
public class GeneralExceptionHandler {

    Logger log = LoggerFactory.getLogger(GeneralExceptionHandler.class);

    @ExceptionHandler(ReportingApiException.class)
    public final ResponseEntity<ErrorResponse> handleApiException(ReportingApiException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setDateTime(LocalDateTime.now());
        errorResponse.setCode(ex.getErrorCode().value());
        errorResponse.setMessage(ex.getErrorCode().name().concat(ex.getMessage()));
        errorResponse.setSessionId(request.getSessionId());
        log.error("ReportingApiException ", ex);
        return new ResponseEntity<>(errorResponse, ex.getErrorCode());
    }

    @ExceptionHandler(BindException.class)
    public ErrorResponse handleBindException(BindException ex, WebRequest request) {
        ErrorResponse message = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.name().concat(ex.getMessage()), request.getSessionId(),
                LocalDateTime.now());

        log.error("BAD_REQUEST_ERROR", ex);
        return message;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse globalExceptionHandler(Exception ex, WebRequest request) {
        ErrorResponse message = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(),
                request.getSessionId(), LocalDateTime.now());

        log.error("INTERNAL_SERVER_ERROR", ex);
        return message;
    }
}
