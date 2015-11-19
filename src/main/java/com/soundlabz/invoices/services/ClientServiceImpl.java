package com.soundlabz.invoices.services;

import com.soundlabz.invoices.domain.Client;
import com.soundlabz.invoices.domain.User;
import com.soundlabz.invoices.domain.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    @Autowired
    public void setClientRepository(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Collection<Client> getAll(User user) {
        return this.clientRepository.findByUserId(user.getId());
    }

    @Override
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client getClient(Long id) {
        return clientRepository.findOne(id);
    }

    @Override
    public Client updateClient(Long  id, Client client) {
       return clientRepository.save(client);
    }
}
