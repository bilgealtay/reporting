package com.ravensoftware.reporting.customer.control;

import com.ravensoftware.reporting.model.entity.Acquirer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by bilga on 21-02-2020
 */
@Repository
public interface AcquirerRepository extends JpaRepository<Acquirer, Long> {
}
