package com.unitedremote.shops.security.jwt;

import java.util.Base64;

public interface JWT_CONSTANTS {
    String SECRET = Base64.getEncoder().encodeToString("secret".getBytes());
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
    Long EXPIRATION_TIME = 846_000_000L;
}
