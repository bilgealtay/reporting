package com.ravensoftware.reporting.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by bilga
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("com.ravensoftware")
@EnableJpaAuditing(auditorAwareRef = "springSecurityAuditorAware")
public class PersistenceConfig {
}
