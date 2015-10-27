package com.soundlabz.invoices.utils;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class TaxCalculatorTest {

    @Test
    public void testComputeSubtotal() throws Exception {
        TaxCalculator taxCalculator = new TaxCalculator();
        BigDecimal tax = new BigDecimal(10.0);
        BigDecimal total = new BigDecimal(1000.0);
        BigDecimal taxPlusTotal = taxCalculator.computeSubtotal(tax, total);

        assertEquals(1100.0f, taxPlusTotal.floatValue(),0.0f);
    }
}