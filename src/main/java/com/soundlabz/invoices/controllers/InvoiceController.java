package com.soundlabz.invoices.controllers;

import com.soundlabz.invoices.domain.Invoice;
import com.soundlabz.invoices.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    private InvoiceService invoiceService;

    @Autowired
    public void setInvoiceService(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @RequestMapping(method = RequestMethod.GET)
    Collection<Invoice> getInvoices() {
        return invoiceService.getInvoices();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    Invoice findInvoice(@PathVariable Long id) {
        return invoiceService.getInvoice(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    Invoice createInvoice(@RequestBody Invoice invoice) {
        return invoiceService.createOrUpdateInvoice(invoice);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    Invoice updateInvoice(@PathVariable Long id, @RequestBody Invoice invoice) {
        return invoiceService.createOrUpdateInvoice(invoice);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void updateInvoice(@PathVariable Long id) {
        invoiceService.deleteInvoice(id);
    }
}
