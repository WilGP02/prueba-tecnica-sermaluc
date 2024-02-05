package com.reto.tecnico.wilmer.sermaluc.model.response;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AuthResponseTest {

    @Test
    public void testGetSetAccessToken() {
        AuthResponse authResponse = new AuthResponse();
        String accessToken = "accessTokenTest";
        authResponse.setAccess_token(accessToken);
        Assertions.assertEquals(accessToken, authResponse.getAccess_token());
    }

    @Test
    public void testGetSetExpiresIn() {
        AuthResponse authResponse = new AuthResponse();
        long expiresIn = 3600;
        authResponse.setExpires_in(expiresIn);
        Assertions.assertEquals(expiresIn, authResponse.getExpires_in());
    }

    @Test
    public void testGetSetTokenType() {
        AuthResponse authResponse = new AuthResponse();
        String tokenType = "Bearer";
        authResponse.setToken_type(tokenType);
        Assertions.assertEquals(tokenType, authResponse.getToken_type());
    }
}