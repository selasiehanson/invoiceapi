package com.soundlabz.invoices.controllers.errors;

import java.util.ArrayList;
import java.util.List;

public class ValidationMessages {

    private List<FieldError> fieldErrors =  new ArrayList<>();

    public List<FieldError> getFieldErrors() {
        return fieldErrors;
    }

    public void addFieldError(String field, String message){
        FieldError fieldError = new FieldError(field, message);
        fieldErrors.add(fieldError);
    }
}
