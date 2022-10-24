package com.ravensoftware.reporting.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ravensoftware.reporting.base.TokenStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by bilga
 */
@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginResponse implements Serializable {
    private String token;
    private TokenStatus status;
}
