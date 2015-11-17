package com.soundlabz.invoices.controllers;

import com.soundlabz.invoices.domain.InvoiceItem;
import com.soundlabz.invoices.domain.repositories.InvoiceItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/invoice-items")
public class InvoiceItemController {

    private InvoiceItemRepository invoiceItemRepository;

    @Autowired
    public void setInvoiceItemRepository(InvoiceItemRepository invoiceItemRepository) {
        this.invoiceItemRepository = invoiceItemRepository;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteInvoiceItem(@PathVariable Long id) {
        InvoiceItem invoiceItem = invoiceItemRepository.findOne(id);
        invoiceItemRepository.delete(invoiceItem);
        return ResponseEntity.ok().build();
    }
}
