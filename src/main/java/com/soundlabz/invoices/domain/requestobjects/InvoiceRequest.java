package com.soundlabz.invoices.domain.requestobjects;

import com.soundlabz.invoices.domain.Invoice;
import com.soundlabz.invoices.domain.InvoiceItem;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

public class InvoiceRequest {

    private Long id;

//    @NotNull(message = "invoice date is required")
//    @NotBlank
    private LocalDate invoiceDate;

    private LocalDate dueDate;

    private BigDecimal tax;

    @Valid
    private Set<InvoiceItemRequest> invoiceItems;

    private Long clientId;

    private Long currencyId;

    private String notes;

    public String invoiceNumber;

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

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public Set<InvoiceItemRequest> getInvoiceItems() {
        return invoiceItems;
    }

    public void setInvoiceItems(Set<InvoiceItemRequest> invoiceItems) {
        this.invoiceItems = invoiceItems;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
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

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Invoice toInvoice() {
        Invoice invoice = new Invoice();

        invoice.setId(this.getId());
        invoice.setTax(this.getTax());
        invoice.setInvoiceDate(this.getInvoiceDate());
        invoice.setDueDate(this.getDueDate());
        invoice.setNotes(this.getNotes());

        invoice.setInvoiceNumber(this.getInvoiceNumber());
        Set<InvoiceItem> items =  this.getInvoiceItems().stream().map(x -> x.toInvoiceItem()).collect(Collectors.toSet());
        invoice.setInvoiceItems(items);
        invoice.getInvoiceItems().stream().forEach(x -> x.setInvoice(invoice));
        return invoice;
    }

}
