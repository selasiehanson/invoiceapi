package com.soundlabz.invoices.services;

import com.soundlabz.invoices.domain.Currency;
import com.soundlabz.invoices.domain.Invoice;
import com.soundlabz.invoices.domain.InvoiceItem;
import com.soundlabz.invoices.domain.Recipient;
import com.soundlabz.invoices.domain.repositories.CurrencyRepository;
import com.soundlabz.invoices.domain.repositories.InvoiceItemRepository;
import com.soundlabz.invoices.domain.repositories.InvoiceRepository;
import com.soundlabz.invoices.domain.repositories.RecipientRepository;
import com.soundlabz.invoices.domain.requestobjects.InvoiceRequest;
import com.soundlabz.invoices.utils.TaxCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private InvoiceRepository invoiceRepository;
    private RecipientRepository recipientRepository;
    private InvoiceItemRepository invoiceItemRepository;
    private CurrencyRepository currencyRepository;
    private HtmlToPdfConverterService htmlToPdfConverterService;


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

    @Autowired
    public void setCurrencyRepository(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Autowired
    public void setHtmlToPdfConverterService(HtmlToPdfConverterService htmlToPdfConverterService) {
        this.htmlToPdfConverterService = htmlToPdfConverterService;
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
    public Invoice createOrUpdateInvoice(InvoiceRequest invoiceRequest) {

        Currency c = currencyRepository.findOne(invoiceRequest.getCurrencyId());
        Recipient r = recipientRepository.findOne(invoiceRequest.getRecipientId());

        Invoice invoice = invoiceRequest.toInvoice();
        invoice.setCurrency(c);
        invoice.setRecipient(r);
        final Set<InvoiceItem> items = invoice.getInvoiceItems().stream().map(item -> {
            item.setPrice(item.getUnitCost().multiply(new BigDecimal(item.getQuantity())));
            return item;
        }).collect(Collectors.toSet());

        BigDecimal total = items.stream().map(x -> x.getUnitCost()
                .multiply(BigDecimal.valueOf(x.getQuantity())))
                .reduce(BigDecimal::add).orElse(new BigDecimal(0.0));

        if (invoice.getTax().floatValue() >= 0.0f) {
            TaxCalculator tc = new TaxCalculator();
            BigDecimal totalPlusTax = tc.computeSubtotal(invoice.getTax(), total);
            invoice.setTotal(totalPlusTax);
            invoice.setSubtotal(total);
        }

        invoiceRepository.save(invoice);
        return invoice;
    }

    @Override
    public void deleteInvoice(Long id) {

    }

    @Override
    public byte[] getPreview() throws IOException {
        String fullOutputFilePath = htmlToPdfConverterService.convert();
        RandomAccessFile f = new RandomAccessFile(fullOutputFilePath, "r");
        byte[] b = new byte[(int) f.length()];
        f.readFully(b);
        return b;
    }
}
