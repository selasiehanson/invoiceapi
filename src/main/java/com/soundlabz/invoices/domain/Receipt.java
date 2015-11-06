package com.soundlabz.invoices.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "receipts")

public class Receipt {

    @Id
    @SequenceGenerator(name = "receipts_idreceipts_seq",
            sequenceName = "receipts_idreceipts_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "receipts_idreceipts_seq")
    @Column(updatable = false)
    private Long id;

    @NotNull
    private String name;

    public Receipt() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
