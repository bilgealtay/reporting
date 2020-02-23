package com.ravensoftware.reporting.security;

public class SecurityConstants {

    public static final String SECRET = "raven";
    //public static final long EXPIRATION_TIME = 432_000_000; // 5 gün
    public static final long EXPIRATION_TIME = 600000; // 10 dk
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
