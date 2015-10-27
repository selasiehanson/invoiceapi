package com.soundlabz.invoices.services;

import com.soundlabz.invoices.domain.Invoice;

import java.util.Collection;

public interface InvoiceService {
    Collection<Invoice> getInvoices();

    Invoice getInvoice(Long id);

    Invoice createOrUpdateInvoice(Invoice invoice);

    void deleteInvoice(Long id);
}
