package com.ravensoftware.reporting.model.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.SimpleObjectIdResolver;
import com.ravensoftware.reporting.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by bilga
 */
@Data
@Entity
@Table(name = "CUSTOMER_INFOS")
@EqualsAndHashCode(callSuper = true)
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

}
