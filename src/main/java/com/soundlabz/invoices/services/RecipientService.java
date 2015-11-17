package com.soundlabz.invoices.services;

import com.soundlabz.invoices.domain.Recipient;

import java.util.Collection;

public interface RecipientService {

    public Collection<Recipient> getAll();

    public Recipient createRecipient(Recipient create);

    public Recipient getRecipient(Long id);

    public Recipient updateRecipient(Long id, Recipient update);
}
