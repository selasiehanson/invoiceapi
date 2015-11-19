package com.soundlabz.invoices.domain.repositories;

import com.soundlabz.invoices.domain.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long > {
    Collection<Invoice> findByUserId(Long userId);
}
