package com.soundlabz.invoices.domain.requestobjects;

import com.soundlabz.invoices.domain.InvoiceItem;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class InvoiceItemRequest {

    private Long id;

    @NotNull
    private String description;

    @NotNull
    private Long quantity;

    @NotNull
    private BigDecimal unitCost;


    public InvoiceItemRequest() {

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

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(BigDecimal unitCost) {
        this.unitCost = unitCost;
    }

    public InvoiceItem toInvoiceItem() {
        InvoiceItem invoiceItem  = new InvoiceItem();
        invoiceItem.setId(this.getId());
        invoiceItem.setDescription(this.getDescription());
        invoiceItem.setQuantity(this.getQuantity());
        invoiceItem.setUnitCost(this.getUnitCost());
        return invoiceItem;
    }
}
