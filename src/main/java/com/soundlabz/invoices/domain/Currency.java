package com.soundlabz.invoices.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "currencies")
public class Currency {

    @Id
    @SequenceGenerator(name = "currencies_id_seq",
            sequenceName = "currencies_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "currencies_id_seq")
    @Column(updatable = false)
    private Long id;

    private String currencyName;

    private String symbol;

    @Size(max = 3)
    private String currencyCode;

    private String isoAlpha2;

    private String isoAlpha3;

    private Integer isoNumeric;

    @NotNull
    private String country;

    @OneToMany(mappedBy = "currency", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Invoice> invoices;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "pk.currency")
    @JsonIgnore
    private Set<UserCurrency> userCurrencies = new HashSet<UserCurrency>(0);

    public Currency() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getIsoAlpha2() {
        return isoAlpha2;
    }

    public void setIsoAlpha2(String isoAlpha2) {
        this.isoAlpha2 = isoAlpha2;
    }

    public String getIsoAlpha3() {
        return isoAlpha3;
    }

    public void setIsoAlpha3(String isoAlpha3) {
        this.isoAlpha3 = isoAlpha3;
    }

    public Integer getIsoNumeric() {
        return isoNumeric;
    }

    public void setIsoNumeric(Integer isoNumeric) {
        this.isoNumeric = isoNumeric;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Set<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(Set<Invoice> invoices) {
        this.invoices = invoices;
    }

    public Set<UserCurrency> getUserCurrencies() {
        return userCurrencies;
    }

    public void setUserCurrencies(Set<UserCurrency> userCurrencies) {
        this.userCurrencies = userCurrencies;
    }
}


