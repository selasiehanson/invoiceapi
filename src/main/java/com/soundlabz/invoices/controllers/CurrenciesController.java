package com.soundlabz.invoices.controllers;

import com.soundlabz.invoices.domain.Currency;
import com.soundlabz.invoices.services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/currencies")
public class CurrenciesController {

    private CurrencyService currencyService;

    @Autowired
    public void setCurrencyService(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Currency> getCurrencies() {
        return currencyService.getCurrencies();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Currency createCurrency(@RequestBody Currency currency) {
        return currencyService.saveCurrency(currency);
    }

}
