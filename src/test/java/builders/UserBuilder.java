package builders;

import com.soundlabz.invoices.domain.Invoice;
import com.soundlabz.invoices.domain.User;

import java.util.Set;

public class UserBuilder {
    private User user = new User();

    public UserBuilder username(String username) {
        user.setUsername(username);
        return this;
    }

    public UserBuilder password(String password) {
        user.setPassword(password);
        return this;
    }

    public UserBuilder id(Long id) {
        user.setId(id);
        return this;
    }

    public UserBuilder invoices(Set<Invoice> invoices) {
        user.setInvoices(invoices);
        return this;
    }

    public User build(){
        return user;
    }

}
