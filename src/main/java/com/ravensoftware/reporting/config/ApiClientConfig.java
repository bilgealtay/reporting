package com.ravensoftware.reporting.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by bilga on 22-10-2022
 */
@Data
@ConfigurationProperties(prefix = "reporting.api")
public class ApiClientConfig {
    private String path;
    private String login;

}
