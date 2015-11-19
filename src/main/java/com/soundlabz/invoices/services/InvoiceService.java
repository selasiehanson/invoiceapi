package com.soundlabz.invoices.services;

import com.soundlabz.invoices.domain.Invoice;
import com.soundlabz.invoices.domain.User;
import com.soundlabz.invoices.domain.requestobjects.InvoiceRequest;

import java.io.IOException;
import java.util.Collection;

public interface InvoiceService {
    Collection<Invoice> getInvoices(User user);

    Invoice getInvoice(Long id);

    Invoice createOrUpdateInvoice(InvoiceRequest invoiceRequest, User user);

    void deleteInvoice(Long id);

    byte[] getPreview() throws IOException;
}
