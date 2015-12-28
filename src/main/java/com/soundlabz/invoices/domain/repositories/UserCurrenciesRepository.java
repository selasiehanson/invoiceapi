package com.soundlabz.invoices.domain.repositories;

import com.soundlabz.invoices.domain.UserCurrency;
import com.soundlabz.invoices.domain.UserCurrencyId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCurrenciesRepository extends JpaRepository<UserCurrency, UserCurrencyId> {

    //    @Query("SELECT uc FROM user_currencies uc where uc.user_id =:id")
//    List<UserCurrency> findByUserId(Long user_id);
}
