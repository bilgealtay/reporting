package com.ravensoftware.reporting.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by bilga on 21-02-2020
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenResponse implements Serializable {

    private String token;
    private TokenStatus status;
}
