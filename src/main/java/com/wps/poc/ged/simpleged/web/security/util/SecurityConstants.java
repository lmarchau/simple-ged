package com.wps.poc.ged.simpleged.web.security.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

public class SecurityConstants {

    public static final String SECRET = "LongSecretKeyToGenJWTsShortKeyCouldBeaSecurityproblem";
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET.getBytes());
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/api/me/signup";
    public static final String RECOVER_PASSWORD_URL = "/api/me/*/recover";
    public static final String CHANGE_PASSWORD_URL = "/api/me/**/changepassword";

    public static String generateToken(String username) {
        return Jwts.builder().setSubject(username).setExpiration(
                new Date(System.currentTimeMillis() + EXPIRATION_TIME)).signWith(SECRET_KEY).compact();
    }
}