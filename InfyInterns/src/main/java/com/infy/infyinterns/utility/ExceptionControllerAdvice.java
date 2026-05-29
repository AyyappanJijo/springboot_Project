package com.infy.infyinterns.utility;

import com.infy.infyinterns.exception.InfyInternException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

/**
 * Centralised exception handler.
 * Every controller method just throws — this class handles the response.
 *
 * Handles:
 *  1. InfyInternException     → 400 with message key resolved from application.properties
 *  2. Validation exceptions   → 400 with all constraint messages joined
 *  3. Everything else         → 500 with a generic safe message
 */
@RestControllerAdvice
public class ExceptionControllerAdvice {

    private static final Log LOGGER = LogFactory.getLog(ExceptionControllerAdvice.class);

    @Autowired
    private Environment environment;

    /* ── 1. Application business exceptions ── */
    @ExceptionHandler(InfyInternException.class)
    public ResponseEntity<ErrorInfo> handleInfyInternException(InfyInternException ex) {
        LOGGER.error("Business exception: " + ex.getMessage(), ex);

        ErrorInfo error = new ErrorInfo();
        error.setErrorCode(HttpStatus.BAD_REQUEST.value());
        // Resolve message key → human-readable text from application.properties
        error.setErrorMessage(environment.getProperty(ex.getMessage()));

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /* ── 2. Bean Validation failures (@Valid on @RequestBody) ── */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorInfo> handleValidationException(MethodArgumentNotValidException ex) {
        LOGGER.error("Validation exception: " + ex.getMessage());

        String messages = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining(" | "));

        ErrorInfo error = new ErrorInfo();
        error.setErrorCode(HttpStatus.BAD_REQUEST.value());
        error.setErrorMessage(messages);

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /* ── 3. Constraint violations (@Validated on path/query params) ── */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorInfo> handleConstraintViolation(ConstraintViolationException ex) {
        LOGGER.error("Constraint violation: " + ex.getMessage());

        String messages = ex.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(" | "));

        ErrorInfo error = new ErrorInfo();
        error.setErrorCode(HttpStatus.BAD_REQUEST.value());
        error.setErrorMessage(messages);

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /* ── 4. Catch-all for unexpected exceptions ── */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorInfo> handleGeneralException(Exception ex) {
        LOGGER.error("Unexpected exception: " + ex.getMessage(), ex);

        ErrorInfo error = new ErrorInfo();
        error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setErrorMessage(environment.getProperty("General.EXCEPTION_MESSAGE"));

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}