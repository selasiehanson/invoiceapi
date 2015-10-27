package com.soundlabz.invoices.domain.repositories;

import com.soundlabz.invoices.domain.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceItemRepository extends JpaRepository<InvoiceItem, Long> {
}
