package com.soundlabz.invoices.services;

import com.soundlabz.invoices.domain.Client;
import com.soundlabz.invoices.domain.User;

import java.util.Collection;

public interface ClientService {


    public Collection<Client> getAll(User user);

    public Client createClient(Client create);

    public Client getClient(Long id);

    public Client updateClient(Long id, Client update);
}
