package com.soundlabz.invoices.domain.repositories;

import com.soundlabz.invoices.domain.Currency;
import org.springframework.data.jpa.repository.JpaRepository;


public interface  CurrencyRepository extends JpaRepository<Currency, Long> {
}
