package com.soundlabz.invoices.services;

import com.soundlabz.invoices.domain.Invoice;
import com.soundlabz.invoices.domain.InvoiceItem;
import com.soundlabz.invoices.domain.Recipient;
import com.soundlabz.invoices.domain.repositories.InvoiceItemRepository;
import com.soundlabz.invoices.domain.repositories.InvoiceRepository;
import com.soundlabz.invoices.domain.repositories.RecipientRepository;
import com.soundlabz.invoices.utils.TaxCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private InvoiceRepository invoiceRepository;
    private RecipientRepository recipientRepository;
    private InvoiceItemRepository invoiceItemRepository;

    @Autowired
    public void setInvoiceRepository(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Autowired
    public void setRecipientRepository(RecipientRepository recipientRepository) {
        this.recipientRepository = recipientRepository;
    }

    @Autowired
    public void setInvoiceItemRepository(InvoiceItemRepository invoiceItemRepository) {
        this.invoiceItemRepository = invoiceItemRepository;
    }

    @Override
    public Collection<Invoice> getInvoices() {
        return invoiceRepository.findAll();
    }

    @Override
    public Invoice getInvoice(Long id) {
        return invoiceRepository.findOne(id);
    }

    @Override
    public Invoice createOrUpdateInvoice(Invoice invoice) {


        final Set<InvoiceItem> items = invoice.getInvoiceItems().stream().map(item -> {
            item.setPrice(item.getUnitCost().multiply(new BigDecimal(item.getQuantity())));
            return item;
        }).collect(Collectors.toSet());

        BigDecimal total = items.stream().map(x -> x.getUnitCost()
                .multiply(BigDecimal.valueOf(x.getQuantity())))
                .reduce((a, b) -> a.add(b)).orElse(new BigDecimal(0.0));

        if (invoice.getTax().floatValue() >= 0.0f) {
            TaxCalculator tc = new TaxCalculator();
            BigDecimal totalPlusTax = tc.computeSubtotal(invoice.getTax(), total);
            invoice.setTotal(totalPlusTax);
        }

        invoiceRepository.save(invoice);
        return invoice;
    }

    @Override
    public void deleteInvoice(Long id) {

    }
}
