package com.ravensoftware.reporting.base;

import java.io.Serializable;

/**
 * Created by bilga on 21-02-2020
 */
public class TokenResponse implements Serializable {

    private String token;
    private TokenStatus status;

    public TokenResponse() {
    }

    public TokenResponse(String token, TokenStatus status) {
        this.token = token;
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public TokenStatus getStatus() {
        return status;
    }

    public void setStatus(TokenStatus status) {
        this.status = status;
    }
}
