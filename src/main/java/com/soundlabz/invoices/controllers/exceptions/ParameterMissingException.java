package com.soundlabz.invoices.controllers.exceptions;

public class ParameterMissingException extends RuntimeException {
    public ParameterMissingException(String error) {
        super(error);
    }
}
