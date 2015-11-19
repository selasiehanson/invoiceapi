package com.soundlabz.invoices.controllers;

import com.soundlabz.invoices.controllers.exceptions.ParameterMissingException;
import com.soundlabz.invoices.domain.Client;
import com.soundlabz.invoices.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/api/clients")
public class ClientsController extends BaseController{

    private ClientService clientService;

    @Autowired
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Client> getAllRecipients() {
        return clientService.getAll(getCurrentUser());
    }

    @RequestMapping(method = RequestMethod.POST)
    public Client createRecipient(@Valid @RequestBody Client client) {
        client.setUser(getCurrentUser());
        return clientService.createClient(client);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Client findInvoice(@PathVariable Long id) {
        return clientService.getClient(id);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Client updateRecipient(@PathVariable Long id, @RequestBody Client client) {
        if (client.getId() == null) {
            throw new ParameterMissingException("id cannot be null");
        }
        return clientService.updateClient(id, client);
    }

}
