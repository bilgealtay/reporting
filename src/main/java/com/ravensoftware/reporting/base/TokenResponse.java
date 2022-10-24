package com.ravensoftware.reporting.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by bilga
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenResponse implements Serializable {

    private String token;
    private TokenStatus status;
}
