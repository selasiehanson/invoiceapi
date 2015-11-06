package com.soundlabz.invoices.domain.repositories;

import com.soundlabz.invoices.domain.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
}
