package builders;

import com.soundlabz.invoices.domain.Client;
import com.soundlabz.invoices.domain.Invoice;
import com.soundlabz.invoices.domain.User;

import java.util.Set;

public class ClientBuilder {
    private Client client = new Client();


    public ClientBuilder id(Long id) {
        client.setId(id);
        return this;
    }

    public ClientBuilder name(String name) {
        client.setName(name);
        return this;
    }

    public ClientBuilder phoneNumber(String phoneNumber) {
        client.setPhoneNumber(phoneNumber);
        return this;
    }

    public ClientBuilder address(String address) {
        client.setAddress(address);
        return this;
    }

    public ClientBuilder email(String email) {
        client.setEmail(email);
        return this;
    }

    public ClientBuilder user(User user) {
        client.setUser(user);
        return this;
    }

    public ClientBuilder invoices(Set<Invoice> invoices) {
        client.setInvoices(invoices);
        return this;
    }

    public Client build() {
        return client;
    }
}
