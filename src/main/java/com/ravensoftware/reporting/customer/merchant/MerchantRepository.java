package com.ravensoftware.reporting.customer.merchant;

import com.ravensoftware.reporting.model.entity.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by bilga
 */
@Repository
public interface MerchantRepository extends JpaRepository<Merchant, Long> {
}
