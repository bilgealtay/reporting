package com.ravensoftware.reporting.model.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.SimpleObjectIdResolver;
import com.ravensoftware.reporting.base.BaseEntity;
import com.ravensoftware.reporting.base.Currency;
import com.ravensoftware.reporting.base.Operation;
import com.ravensoftware.reporting.base.PaymentMethod;
import com.ravensoftware.reporting.model.enums.ErrorCode;
import com.ravensoftware.reporting.model.enums.TransactionStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by bilga on 21-02-2020
 */
@Data
@Entity
@Table(name = "TRANSACTIONS")
@EqualsAndHashCode(callSuper = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Transaction.class, resolver = SimpleObjectIdResolver.class)
public class Transaction extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRANSACTIONS_ID_SEQ")
    @SequenceGenerator(name = "TRANSACTIONS_ID_SEQ", sequenceName = "TRANSACTIONS_ID_SEQ", allocationSize = 1)
    private Long id;

    @Column(name="TRANSACTION_NO")
    private String transactionNo;

    @Column(name="AMOUNT")
    private BigDecimal amount;

    @Column(name="REFERENCE_NO")
    private String referenceNo;

    @Column(name="ERROR_CODE")
    @Enumerated(EnumType.STRING)
    private ErrorCode errorCode;

    @Column(name="CURRENCY")
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Column(name="TRANSACTION_STATUS")
    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;

    @Column(name="OPERATION")
    @Enumerated(EnumType.STRING)
    private Operation operation;

    @Column(name="PAYMENT_METHOD")
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Column(name="TX_DATE")
    private LocalDate transactionDate;;

    @ManyToOne
    @JoinColumn(name = "MERCHANT_ID")
    private Merchant merchant;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_INFO_ID")
    private CustomerInfo customerInfo;

    @ManyToOne
    @JoinColumn(name = "ACQUIRER_ID")
    private Acquirer acquirer;

}
