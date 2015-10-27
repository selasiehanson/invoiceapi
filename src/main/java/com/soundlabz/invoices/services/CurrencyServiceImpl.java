package com.soundlabz.invoices.services;

import com.soundlabz.invoices.domain.Currency;
import com.soundlabz.invoices.domain.repositories.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private CurrencyRepository currencyRepository;

    @Autowired
    public void setCurrencyRepository(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public Collection<Currency> getCurrencies() {
        return currencyRepository.findAll();
    }

    @Override
    public Currency getCurrency(Long id) {
        return currencyRepository.findOne(id);
    }

    @Override
    public Currency saveCurrency(Currency currency) {
        return currencyRepository.save(currency);
    }
}
