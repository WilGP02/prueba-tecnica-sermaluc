package com.reto.tecnico.wilmer.sermaluc.controller;

import com.reto.tecnico.wilmer.sermaluc.model.request.AuthRequest;
import com.reto.tecnico.wilmer.sermaluc.model.response.AuthResponse;
import com.reto.tecnico.wilmer.sermaluc.service.IAuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AuthControllerTest {

    @Mock
    private IAuthService authService;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void authentication_ValidAuthRequest_ReturnsOkResponse() {
        // Arrange
        AuthRequest authRequest = new AuthRequest();
        authRequest.setEmail("test@example.com");
        authRequest.setPassword("password");

        AuthResponse authResponse = new AuthResponse();
        authResponse.setAccess_token("token");
        authResponse.setExpires_in(3600);
        authResponse.setToken_type("Bearer");

        when(authService.authenticate(authRequest)).thenReturn(authResponse);

        // Act
        ResponseEntity<?> responseEntity = authController.authentication(authRequest);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(authResponse, responseEntity.getBody());
        verify(authService, times(1)).authenticate(authRequest);
    }
}