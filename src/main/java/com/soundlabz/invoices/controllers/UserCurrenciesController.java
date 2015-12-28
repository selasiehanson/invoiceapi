package com.soundlabz.invoices.controllers;

import com.soundlabz.invoices.domain.Currency;
import com.soundlabz.invoices.domain.User;
import com.soundlabz.invoices.domain.UserCurrency;
import com.soundlabz.invoices.domain.repositories.CurrencyRepository;
import com.soundlabz.invoices.domain.repositories.UserCurrenciesRepository;
import com.soundlabz.invoices.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user-currencies")
public class UserCurrenciesController extends BaseController {

    private UserCurrenciesRepository userCurrenciesRepository;
    private CurrencyRepository currencyRepository;
    private UserRepository userRepository;

    @Autowired
    public UserCurrenciesController(UserCurrenciesRepository userCurrenciesRepository,
                                    UserRepository userRepository,
                                    CurrencyRepository currencyRepository) {

        this.userCurrenciesRepository = userCurrenciesRepository;
        this.userRepository = userRepository;
        this.currencyRepository = currencyRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<UserCurrency> getUsersCurrency() {
        return userCurrenciesRepository.findAll()
                .stream().filter(uc -> uc.getUser().getId() == getCurrentUser().getId())
                .collect(Collectors.toList());
        //return getCurrentUser().getUserCurrencies();
    }

    @RequestMapping(method = RequestMethod.POST)
//    @Transactional
    public ResponseEntity createUserCurrencies(@RequestBody Map<String, List<Long>> data) {
        List<Long> ids = data.get("currencyIds");
        if (ids == null) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        User currentUser = getCurrentUser();
        for (int i = 0; i < ids.size(); ++i) {
            Long id = ids.get(i);
            Currency currency = currencyRepository.findOne(id); //currencyService.getCurrency(id);

            UserCurrency userCurrency = new UserCurrency();
            userCurrency.setUser(currentUser);
            userCurrency.setCurrency(currency);
            userCurrenciesRepository.save(userCurrency);
        }

        return ResponseEntity.ok().build();
    }
}

