package com.infy.infyinterns.exception;

/**
 * Application-level checked exception.
 * Always carries a message key that maps to a human-readable string
 * in application.properties (e.g. "Service.MENTOR_NOT_FOUND").
 */
public class InfyInternException extends Exception {

    private static final long serialVersionUID = 1L;

    public InfyInternException(String message) {
        super(message);
    }
}