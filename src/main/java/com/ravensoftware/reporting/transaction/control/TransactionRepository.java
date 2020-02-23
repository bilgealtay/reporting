package com.ravensoftware.reporting.transaction.control;

import com.ravensoftware.reporting.contact.entity.CustomerInfo;
import com.ravensoftware.reporting.transaction.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by bilga on 21-02-2020
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>, JpaSpecificationExecutor<Transaction> {

    @Query("SELECT c.customerInfo FROM Transaction c WHERE c.id=:id")
    CustomerInfo getCustomerInfo(Long id);
}
