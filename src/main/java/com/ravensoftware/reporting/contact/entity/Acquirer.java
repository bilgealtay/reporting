package com.ravensoftware.reporting.contact.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.SimpleObjectIdResolver;
import com.ravensoftware.reporting.base.BaseEntity;
import com.ravensoftware.reporting.base.PaymentMethod;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by bilga on 21-02-2020
 */
@Entity
@Table(name = "ACQUIRERS")
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

    public PaymentMethod getType() {
        return type;
    }

    public void setType(PaymentMethod type) {
        this.type = type;
    }
}
