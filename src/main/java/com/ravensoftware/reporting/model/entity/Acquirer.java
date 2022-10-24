package com.ravensoftware.reporting.model.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.SimpleObjectIdResolver;
import com.ravensoftware.reporting.base.BaseEntity;
import com.ravensoftware.reporting.base.PaymentMethod;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by bilga on 21-02-2020
 */
@Data
@Entity
@Table(name = "ACQUIRERS")
@EqualsAndHashCode(callSuper = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Acquirer.class, resolver = SimpleObjectIdResolver.class)
public class Acquirer extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACQUIRERS_ID_SEQ")
    @SequenceGenerator(name = "ACQUIRERS_ID_SEQ", sequenceName = "ACQUIRERS_ID_SEQ", allocationSize = 1)
    private Long id;

    @Column(name="NAME")
    private String name;

    @Column(name="CODE")
    private String code;

    @Column(name="TYPE")
    @Enumerated(EnumType.STRING)
    private PaymentMethod type;

}
