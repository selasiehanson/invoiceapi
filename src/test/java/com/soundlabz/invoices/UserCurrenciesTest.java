package com.soundlabz.invoices;

import builders.UserBuilder;
import com.soundlabz.invoices.domain.Currency;
import com.soundlabz.invoices.domain.User;
import com.soundlabz.invoices.domain.UserCurrency;
import com.soundlabz.invoices.domain.repositories.CurrencyRepository;
import com.soundlabz.invoices.domain.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = InvoiceApplication.class)
@ActiveProfiles("test")
public class UserCurrenciesTest {


    private static final String username = "Sammy Dee";
    private static final String password = "12345567890";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CurrencyRepository currencyRepository;

    private User user;
    private Currency currency;

    @Before
    public void setup() {

        userRepository.deleteAll();
        currencyRepository.deleteAll();

        user = new UserBuilder()
                .username(username)
                .password(password)
                .build();

        currency = new Currency();
        //( 'Ghana', 'GH', 'GHA', 288, 'GHC', 'Cedi', '¢')
        currency.setCountry("GHANA");
        currency.setIsoAlpha2("GH");
        currency.setIsoAlpha3("GHA");
        currency.setIsoNumeric(288);
        currency.setCurrencyCode("GHC");
        currency.setCurrencyName("cedi");
        currency.setSymbol("¢");


        currencyRepository.save(currency);
        userRepository.save(user);
    }

    @Test
    public void userHasSomeCurrencies() {

        User currentUser = userRepository.findOne(user.getId());
        Currency firstCurrency = currencyRepository.findOne(currency.getId());

        UserCurrency userCurrency = new UserCurrency();
        userCurrency.setCurrency(firstCurrency);
        userCurrency.setUser(currentUser);
        currentUser.getUserCurrencies().add(userCurrency);

        userRepository.save(currentUser);

        User testUser = userRepository.findOne(currentUser.getId());
        assertEquals(1,testUser.getUserCurrencies().size());
    }
}
