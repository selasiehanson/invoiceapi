package com.soundlabz.invoices.domain;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_currencies")
@AssociationOverrides({
        @AssociationOverride(name = "pk.user", joinColumns = @JoinColumn(name = "user_id")),
        @AssociationOverride(name = "pk.currency", joinColumns = @JoinColumn(name = "currency_id"))
})
public class UserCurrency implements Serializable {

    @EmbeddedId
    private UserCurrencyId pk = new UserCurrencyId();

    public UserCurrencyId getPk() {
        return pk;
    }

    public void setPk(UserCurrencyId pk) {
        this.pk = pk;
    }

    //@Column(name = "created_at")
    //private DateTime createdAt;

    @Transient
    public User getUser() {
        return getPk().getUser();
    }

    public void setUser(User user) {
        getPk().setUser(user);
    }

    @Transient
    public Currency getCurrency() {
        return getPk().getCurrency();
    }

    public void setCurrency(Currency currency) {
        getPk().setCurrency(currency);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserCurrency that = (UserCurrency) o;

        return !(pk != null ? !pk.equals(that.pk) : that.pk != null);

    }

    @Override
    public int hashCode() {
        return pk != null ? pk.hashCode() : 0;
    }
}
