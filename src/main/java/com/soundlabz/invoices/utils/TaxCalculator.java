package com.soundlabz.invoices.utils;

import java.math.BigDecimal;

public class TaxCalculator {

    public BigDecimal computeSubtotal(BigDecimal tax, BigDecimal total) {
        //assume tax is a percentage
        float taxAmount = tax.floatValue() / 100f;
        return new BigDecimal(taxAmount).multiply(total).add(total);
    }
}
