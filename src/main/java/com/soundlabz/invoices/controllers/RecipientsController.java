package com.soundlabz.invoices.controllers;

import com.soundlabz.invoices.domain.Recipient;
import com.soundlabz.invoices.services.RecipientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Recipient createRecipient(@Valid @RequestBody Recipient recipient) {
        return recipientService.createRecipient(recipient);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Recipient findInvoice(@PathVariable Long id) {
        return recipientService.getRecipient(id);
    }
}
