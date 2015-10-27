package com.soundlabz.invoices.domain.repositories;

import com.soundlabz.invoices.domain.Recipient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipientRepository extends JpaRepository<Recipient, Long> {
}
