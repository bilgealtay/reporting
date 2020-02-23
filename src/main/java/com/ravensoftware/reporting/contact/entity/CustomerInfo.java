package com.ravensoftware.reporting.contact.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.SimpleObjectIdResolver;
import com.ravensoftware.reporting.base.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by bilga on 21-02-2020
 */
@Entity
@Table(name = "CUSTOMER_INFOS")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = CustomerInfo.class, resolver = SimpleObjectIdResolver.class)
public class CustomerInfo extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUSTOMER_INFOS_ID_SEQ")
    @SequenceGenerator(name = "CUSTOMER_INFOS_ID_SEQ", sequenceName = "CUSTOMER_INFOS_ID_SEQ", allocationSize = 1)
    private Long id;

    @Column(name="NUMBER")
    private String number;

    @Column(name="EMAIL")
    private String email;

    @Column(name="BILLING_FIRST_NAME")
    private String billingFirstName;

    @Column(name="BILLING_LAST_NAME")
    private String billingLastName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBillingFirstName() {
        return billingFirstName;
    }

    public void setBillingFirstName(String billingFirstName) {
        this.billingFirstName = billingFirstName;
    }

    public String getBillingLastName() {
        return billingLastName;
    }

    public void setBillingLastName(String billingLastName) {
        this.billingLastName = billingLastName;
    }
}
