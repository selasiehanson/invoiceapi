package com.soundlabz.invoices.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "invoice_items")
public class InvoiceItem {

    @Id
    @SequenceGenerator(name = "invoice_items_id_seq", sequenceName = "invoice_items_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "invoice_items_id_seq")
    @Column(updatable = false)
    private Long id;

    @NotNull
//    @NotBlank
    private String description;

    @Column(precision = 15, scale = 2)
    @NotNull
//    @NotBlank
    private BigDecimal unitCost;

    @NotNull
//    @NotBlank
    private Long quantity;


    @Column(precision = 15, scale = 2)
    @NotNull
//    @NotBlank
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    @JsonIgnore
    private Invoice invoice;

    public InvoiceItem() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(BigDecimal unitCost) {
        this.unitCost = unitCost;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Long getInvoiceId(){
        return invoice.getId();
    }
}
