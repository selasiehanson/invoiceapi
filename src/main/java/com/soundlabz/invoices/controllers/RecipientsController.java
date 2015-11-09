package com.soundlabz.invoices.controllers;

import com.soundlabz.invoices.domain.Recipient;
import com.soundlabz.invoices.services.RecipientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/api/recipients")
public class RecipientsController {

    private RecipientService recipientService;

    @Autowired
    public void setRecipientService(RecipientService recipientService) {
        this.recipientService = recipientService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Recipient> getAllRecipients() {
        return recipientService.getAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createRecipient(@Valid @RequestBody Recipient recipient, Errors errors) {
//        if (errors.hasErrors()) {
//            return new ResponseEntity(new ApiErrors(errors), HttpStatus.BAD_REQUEST);
//        }
        return new ResponseEntity(recipientService.createRecipient(recipient), HttpStatus.CREATED);
    }
}
