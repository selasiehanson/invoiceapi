package com.soundlabz.invoices.controllers.errors;

public class FieldError {

    private String message;

    private String field;

    public FieldError(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}
