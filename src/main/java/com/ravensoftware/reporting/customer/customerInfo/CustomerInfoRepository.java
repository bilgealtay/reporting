package com.ravensoftware.reporting.customer.customerInfo;

import com.ravensoftware.reporting.model.entity.CustomerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by bilga
 */
@Repository
public interface CustomerInfoRepository extends JpaRepository<CustomerInfo, Long> {

    CustomerInfo findByEmail(String email);
}
