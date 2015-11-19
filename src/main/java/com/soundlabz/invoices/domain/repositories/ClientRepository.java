package com.soundlabz.invoices.domain.repositories;

import com.soundlabz.invoices.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface ClientRepository extends JpaRepository<Client, Long > {

    Collection<Client> findByUserId(Long userId);
}
