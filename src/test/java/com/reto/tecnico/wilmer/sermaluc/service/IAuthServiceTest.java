package com.reto.tecnico.wilmer.sermaluc.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import com.reto.tecnico.wilmer.sermaluc.model.request.AuthRequest;
import com.reto.tecnico.wilmer.sermaluc.model.response.AuthResponse;

public class IAuthServiceTest {

    private IAuthService authService;
    private AuthRequest authRequest;

    @BeforeEach
    public void setup() {
        authService = Mockito.mock(IAuthService.class);
        authRequest = new AuthRequest();
        authRequest.setEmail("wilmer_palomino@gmail.com");
        authRequest.setPassword("elmaestro");
    }

    @Test
    public void testAuthenticateSuccess() {
        AuthResponse expectedResponse = new AuthResponse();
        expectedResponse.setAccess_token("access_token");
        expectedResponse.setExpires_in(3600);
        expectedResponse.setToken_type("Bearer");

        Mockito.when(authService.authenticate(authRequest)).thenReturn(expectedResponse);

        AuthResponse actualResponse = authService.authenticate(authRequest);

        Assertions.assertEquals(expectedResponse.getAccess_token(), actualResponse.getAccess_token());
        Assertions.assertEquals(expectedResponse.getExpires_in(), actualResponse.getExpires_in());
        Assertions.assertEquals(expectedResponse.getToken_type(), actualResponse.getToken_type());
    }

    @Test
    public void testAuthenticateFailure() {
        Mockito.when(authService.authenticate(authRequest)).thenReturn(null);

        AuthResponse actualResponse = authService.authenticate(authRequest);

        Assertions.assertNull(actualResponse);
    }
}