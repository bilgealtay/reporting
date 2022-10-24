package com.ravensoftware.reporting.customer.control;

import com.ravensoftware.reporting.model.entity.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by bilga on 21-02-2020
 */
@Repository
public interface MerchantRepository extends JpaRepository<Merchant, Long> {
}