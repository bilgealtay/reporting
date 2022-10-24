package com.ravensoftware.reporting.user.control;

import com.ravensoftware.reporting.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by bilga
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String username);
}
