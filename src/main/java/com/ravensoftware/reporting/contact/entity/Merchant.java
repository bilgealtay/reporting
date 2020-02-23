package com.ravensoftware.reporting.contact.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.SimpleObjectIdResolver;
import com.ravensoftware.reporting.base.Currency;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by bilga on 21-02-2020
 */
@Entity
@Table(name = "MERCHANTS")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Merchant.class, resolver = SimpleObjectIdResolver.class)
public class Merchant {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACQUIRERS_ID_SEQ")
    @SequenceGenerator(name = "ACQUIRERS_ID_SEQ", sequenceName = "ACQUIRERS_ID_SEQ", allocationSize = 1)
    private Long id;

    @Column(name="NAME")
    private String name;

    @Column(name="CODE")
    private String code;

    @Column(name="AMOUNT")
    private BigDecimal originalAmount;

    @Column(name="ORIGINAL_CURRENCY")
    @Enumerated(EnumType.STRING)
    private Currency originalCurrency;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getOriginalAmount() {
        return originalAmount;
    }

    public void setOriginalAmount(BigDecimal originalAmount) {
        this.originalAmount = originalAmount;
    }

    public Currency getOriginalCurrency() {
        return originalCurrency;
    }

    public void setOriginalCurrency(Currency originalCurrency) {
        this.originalCurrency = originalCurrency;
    }
}
