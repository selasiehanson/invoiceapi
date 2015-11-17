package com.soundlabz.invoices.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.soundlabz.invoices.utils.JsonDateSerializer;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "invoices")
public class Invoice {

    @Id
    @SequenceGenerator(name = "invoices_id_seq",
            sequenceName = "invoices_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "invoices_id_seq")
    @Column(updatable = false)
    private Long id;

    @NotNull(message = "invoice date is required")
    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate invoiceDate;

    @Column(precision = 15, scale = 2)
    private BigDecimal subtotal;

    @Column(columnDefinition = "Decimal(15,2) default '0.00'")
    @NotNull
    private BigDecimal tax;

    @Column(precision = 15, scale = 2)
    @NotNull
    private BigDecimal total;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
    private Set<InvoiceItem> invoiceItems;

    @ManyToOne
    @JoinColumn(name = "recipient_id")
    @JsonIgnore
    private Recipient recipient;

    @ManyToOne
    @JoinColumn(name = "currency_id")
    @JsonIgnore
    private Currency currency;

    private String notes;

    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dueDate;

    public Invoice() {
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

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Set<InvoiceItem> getInvoiceItems() {
        return invoiceItems;
    }

    public void setInvoiceItems(Set<InvoiceItem> invoiceItems) {
        this.invoiceItems = invoiceItems;
    }

    public Recipient getRecipient() {
        return recipient;
    }

    public void setRecipient(Recipient recipient) {
        this.recipient = recipient;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Long getCurrencyId() {
        return currency.getId();
    }

    public Long getRecipientId() {
        return recipient.getId();
    }

    public String getRecipientName() {
        return recipient.getName();
    }

    public String getCurrencyName() {
        return currency.getCurrencyName();
    }


    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
