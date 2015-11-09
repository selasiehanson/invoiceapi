package com.soundlabz.invoices.controllers.errors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Locale;

@ControllerAdvice
public class GlobalExceptionController {

    private MessageSource messageSource;

    @Autowired
    public GlobalExceptionController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationMessages handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        ValidationMessages validationMessages = new ValidationMessages();
        BindingResult result = exception.getBindingResult();

        for (FieldError fieldError : result.getFieldErrors()) {
            validationMessages.addFieldError(fieldError.getField(), messageSource.getMessage(fieldError, LocaleContextHolder.getLocale()));
        }

        for (ObjectError globalError : result.getGlobalErrors()) {
            validationMessages.addFieldError(globalError.getObjectName(), globalError.getDefaultMessage());
        }

        return validationMessages;
    }

    private String resolveLocalizedErrorMessage(FieldError fieldError) {
        Locale currentLocale = LocaleContextHolder.getLocale();

        String localizedErrorMessage = messageSource.getMessage(fieldError, currentLocale);
        if (localizedErrorMessage.equals(fieldError.getDefaultMessage())) {
            String[] fieldErrorCodes = fieldError.getCodes();
            localizedErrorMessage = fieldErrorCodes[0];
        }
        return localizedErrorMessage;
    }

}
