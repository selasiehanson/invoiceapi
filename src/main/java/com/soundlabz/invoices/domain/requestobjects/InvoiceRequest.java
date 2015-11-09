package com.soundlabz.invoices.domain.requestobjects;

import com.soundlabz.invoices.domain.Invoice;
import com.soundlabz.invoices.domain.InvoiceItem;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

public class InvoiceRequest {

    private Long id;

//    @NotNull(message = "invoice date is required")
    @NotBlank
    private LocalDate invoiceDate;

    private BigDecimal tax;

    @Valid
    private Set<InvoiceItem> invoiceItems;

    private Long recipientId;

    private Long currencyId;

    private String notes;

    public InvoiceRequest() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public Set<InvoiceItem> getInvoiceItems() {
        return invoiceItems;
    }

    public void setInvoiceItems(Set<InvoiceItem> invoiceItems) {
        this.invoiceItems = invoiceItems;
    }

    public Long getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(Long recipientId) {
        this.recipientId = recipientId;
    }

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Invoice toInvoice() {
        Invoice invoice = new Invoice();

        invoice.setId(this.getId());
        invoice.setTax(this.getTax());
        invoice.setInvoiceDate(this.getInvoiceDate());
        invoice.setNotes(this.getNotes());
        invoice.setInvoiceItems(this.getInvoiceItems());
        invoice.getInvoiceItems().stream().forEach(x -> x.setInvoice(invoice));
        return invoice;
    }

}
