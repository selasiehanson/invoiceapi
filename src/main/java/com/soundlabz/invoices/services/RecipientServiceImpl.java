package com.soundlabz.invoices.services;

import com.soundlabz.invoices.domain.Recipient;
import com.soundlabz.invoices.domain.repositories.RecipientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class RecipientServiceImpl implements RecipientService {

    private RecipientRepository recipientRepository;

    @Autowired
    public void setRecipientRepository(RecipientRepository recipientRepository) {
        this.recipientRepository = recipientRepository;
    }

    @Override
    public Collection<Recipient> getAll() {
        return this.recipientRepository.findAll();
    }

    @Override
    public Recipient createRecipient(Recipient recipient) {
        return recipientRepository.save(recipient);
    }

    @Override
    public Recipient updateRecipient(Long id, Recipient recipient) {
        recipientRepository.save(recipient);
        return null;
    }
}
