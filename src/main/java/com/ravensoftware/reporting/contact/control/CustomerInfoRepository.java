package com.ravensoftware.reporting.contact.control;

import com.ravensoftware.reporting.contact.entity.CustomerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by bilga on 21-02-2020
 */
@Repository
public interface CustomerInfoRepository extends JpaRepository<CustomerInfo, Long> {

    CustomerInfo findByEmail(String email);
}
