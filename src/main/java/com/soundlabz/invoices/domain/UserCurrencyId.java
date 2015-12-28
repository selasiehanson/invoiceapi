package com.soundlabz.invoices.domain;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class UserCurrencyId implements java.io.Serializable {

    @ManyToOne
    private User user;

    @ManyToOne
    private Currency currency;

    public UserCurrencyId() {

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserCurrencyId that = (UserCurrencyId) o;

        if (!user.equals(that.user)) return false;
        return currency.equals(that.currency);

    }

    @Override
    public int hashCode() {
        int result = user.hashCode();
        result = 31 * result + currency.hashCode();
        return result;
    }
}
