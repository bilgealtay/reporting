package com.ravensoftware.reporting.transaction.control;

import com.ravensoftware.reporting.base.Operation;
import com.ravensoftware.reporting.base.PaymentMethod;
import com.ravensoftware.reporting.transaction.entity.ErrorCode;
import com.ravensoftware.reporting.transaction.entity.Transaction;
import com.ravensoftware.reporting.transaction.entity.TransactionStatus;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

/**
 * Created by bilga on 23-02-2020
 */
public class TransactionSpecification {

    public static Specification<Transaction> fromTo(LocalDate from, LocalDate to) {
        return (Specification<Transaction>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.between(root.get("txDate"), from, to);
    }

    public static Specification<Transaction> merchant(Long id) {
        return (Specification<Transaction>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("merchant"), id);
    }

    public static Specification<Transaction> acquirer(Long id) {
        return (Specification<Transaction>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("acquirer"), id);
    }

    public static Specification<Transaction> customer(Long id) {
        return (Specification<Transaction>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("customerInfo"), id);
    }

    public static Specification<Transaction> status(TransactionStatus transactionStatus) {
        return (Specification<Transaction>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("transactionStatus"), transactionStatus);
    }

    public static Specification<Transaction> operation(Operation operation) {
        return (Specification<Transaction>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("operation"), operation);
    }

    public static Specification<Transaction> paymentMethod(PaymentMethod paymentMethod) {
        return (Specification<Transaction>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("paymentMethod"), paymentMethod);
    }

    public static Specification<Transaction> errorCode(ErrorCode errorCode) {
        return (Specification<Transaction>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("errorCode"), errorCode);
    }

    public static Specification<Transaction> filter(String field, String value) {
        return (Specification<Transaction>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(field), value);
    }
}
