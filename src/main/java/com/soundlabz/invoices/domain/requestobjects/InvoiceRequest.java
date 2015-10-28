package com.soundlabz.invoices.domain.requestobjects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.soundlabz.invoices.domain.Currency;
import com.soundlabz.invoices.domain.Invoice;
import com.soundlabz.invoices.domain.InvoiceItem;
import com.soundlabz.invoices.domain.Recipient;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

public class InvoiceRequest {

    private Long id;

    private Date invoiceDate;

    private BigDecimal tax;

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

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
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
