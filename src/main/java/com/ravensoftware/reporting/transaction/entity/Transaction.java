package com.ravensoftware.reporting.transaction.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.SimpleObjectIdResolver;
import com.ravensoftware.reporting.base.BaseEntity;
import com.ravensoftware.reporting.base.PaymentMethod;
import com.ravensoftware.reporting.base.Currency;
import com.ravensoftware.reporting.base.Operation;
import com.ravensoftware.reporting.contact.entity.Acquirer;
import com.ravensoftware.reporting.contact.entity.CustomerInfo;
import com.ravensoftware.reporting.contact.entity.Merchant;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by bilga on 21-02-2020
 */
@Entity
@Table(name = "TRANSACTIONS")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Transaction.class, resolver = SimpleObjectIdResolver.class)
public class Transaction extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRANSACTIONS_ID_SEQ")
    @SequenceGenerator(name = "TRANSACTIONS_ID_SEQ", sequenceName = "TRANSACTIONS_ID_SEQ", allocationSize = 1)
    private Long id;

    @Column(name="TRANSACTION_NO")
    private String transactionNo;

    @Column(name="AMOUNT")
    private Long amount;

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
    @JsonIgnore
    private LocalDate txDate;

    @Transient
    private String txDateStr;

    @ManyToOne
    @JoinColumn(name = "MERCHANT_ID")
    private Merchant merchant;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_INFO_ID")
    private CustomerInfo customerInfo;

    @ManyToOne
    @JoinColumn(name = "ACQUIRER_ID")
    private Acquirer acquirer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public LocalDate getTxDate() {
        return txDate;
    }

    public void setTxDate(LocalDate txDate) {
        this.txDate = txDate;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }

    public Acquirer getAcquirer() {
        return acquirer;
    }

    public void setAcquirer(Acquirer acquirer) {
        this.acquirer = acquirer;
    }

    public String getTxDateStr() {
        return txDateStr;
    }

    public void setTxDateStr(String txDateStr) {
        this.txDateStr = txDateStr;
    }
}
