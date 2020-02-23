package com.ravensoftware.reporting.user.control;

import com.ravensoftware.reporting.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by bilga on 20-02-2020
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String username);
}
