package com.soundlabz.invoices.domain.repositories;

import com.soundlabz.invoices.domain.UserCompany;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCompanyRepository extends JpaRepository<UserCompany, Long> {
    UserCompany findByUserId(Long userId);
}
