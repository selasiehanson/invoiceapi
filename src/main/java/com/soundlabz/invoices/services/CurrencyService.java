package com.soundlabz.invoices.services;

import com.soundlabz.invoices.domain.Currency;
import sun.util.resources.cldr.ebu.CurrencyNames_ebu;

import java.util.Collection;

public interface CurrencyService {

    Collection<Currency> getCurrencies();

    Currency getCurrency(Long id);

    Currency saveCurrency(Currency currency);
}
