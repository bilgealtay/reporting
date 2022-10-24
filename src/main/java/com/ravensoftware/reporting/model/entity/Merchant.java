package com.ravensoftware.reporting.model.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.SimpleObjectIdResolver;
import com.ravensoftware.reporting.base.BaseEntity;
import com.ravensoftware.reporting.base.Currency;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by bilga
 */
@Data
@Entity
@Table(name = "MERCHANTS")
@EqualsAndHashCode(callSuper = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Merchant.class, resolver = SimpleObjectIdResolver.class)
public class Merchant extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MERCHANTS_ID_SEQ")
    @SequenceGenerator(name = "MERCHANTS_ID_SEQ", sequenceName = "MERCHANTS_ID_SEQ", allocationSize = 1)
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

}
