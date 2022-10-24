package com.ravensoftware.reporting.customer.acquirer;

import com.ravensoftware.reporting.model.entity.Acquirer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by bilga
 */
@Repository
public interface AcquirerRepository extends JpaRepository<Acquirer, Long> {
}
