package com.ravensoftware.reporting.transaction;

import com.ravensoftware.reporting.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by bilga
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>, JpaSpecificationExecutor<Transaction> {

    @Query("SELECT e FROM Transaction e INNER JOIN FETCH e.customerInfo WHERE e.id = :id")
    Optional<Transaction> findByIdFetchCustomerInfo(@Param("id") Long id);
}
