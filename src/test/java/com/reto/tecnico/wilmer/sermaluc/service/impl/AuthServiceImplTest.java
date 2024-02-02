package com.reto.tecnico.wilmer.sermaluc.service.impl;

import com.reto.tecnico.wilmer.sermaluc.exceptions.CustomException;
import com.reto.tecnico.wilmer.sermaluc.model.Users;
import com.reto.tecnico.wilmer.sermaluc.model.request.AuthRequest;
import com.reto.tecnico.wilmer.sermaluc.model.response.AuthResponse;
import com.reto.tecnico.wilmer.sermaluc.repository.IUserRepository;
import com.reto.tecnico.wilmer.sermaluc.security.services.JwtUtilService;
import com.reto.tecnico.wilmer.sermaluc.service.IAuthService;
import com.reto.tecnico.wilmer.sermaluc.service.impl.AuthServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthServiceImplTest {

    @Mock
    private IUserRepository userRepository;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserDetailsService userDetailsService;

    @Mock
    private JwtUtilService jwtUtilService;

    @InjectMocks
    private AuthServiceImpl authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void authenticate_ValidCredentials_ReturnsAuthResponse() {
        // Arrange
        AuthRequest authRequest = new AuthRequest();
        authRequest.setEmail("test@example.com");
        authRequest.setPassword("password");

        Users user = new Users();
        user.setEmail("test@example.com");
        user.setPassword("password");
        user.setIsActive(true);

        when(userRepository.findByEmail(authRequest.getEmail())).thenReturn(Optional.of(user));

        UserDetails userDetails = mock(UserDetails.class);
        when(userDetailsService.loadUserByUsername(authRequest.getEmail())).thenReturn(userDetails);

        AuthResponse expectedResponse = new AuthResponse();
        when(jwtUtilService.generateToken(userDetails)).thenReturn(expectedResponse);

        // Act
        AuthResponse actualResponse = authService.authenticate(authRequest);

        // Assert
        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);
        verify(userRepository, times(1)).findByEmail(authRequest.getEmail());
        verify(userDetailsService, times(1)).loadUserByUsername(authRequest.getEmail());
        verify(jwtUtilService, times(1)).generateToken(userDetails);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void authenticate_InvalidCredentials_ThrowsCustomException() {
        // Arrange
        AuthRequest authRequest = new AuthRequest();
        authRequest.setEmail("test@example.com");
        authRequest.setPassword("password");

        when(userRepository.findByEmail(authRequest.getEmail())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(CustomException.class, () -> authService.authenticate(authRequest));
        verify(userRepository, times(1)).findByEmail(authRequest.getEmail());
        verify(userDetailsService, never()).loadUserByUsername(anyString());
        verify(jwtUtilService, never()).generateToken(any(UserDetails.class));
        verify(userRepository, never()).save(any(Users.class));
    }

    @Test
    void authenticate_InactiveUser_ThrowsCustomException() {
        // Arrange
        AuthRequest authRequest = new AuthRequest();
        authRequest.setEmail("test@example.com");
        authRequest.setPassword("password");

        Users user = new Users();
        user.setEmail("test@example.com");
        user.setPassword("password");
        user.setIsActive(false);

        when(userRepository.findByEmail(authRequest.getEmail())).thenReturn(Optional.of(user));

        // Act & Assert
        assertThrows(CustomException.class, () -> authService.authenticate(authRequest));
        verify(userRepository, times(1)).findByEmail(authRequest.getEmail());
        verify(userDetailsService, never()).loadUserByUsername(anyString());
        verify(jwtUtilService, never()).generateToken(any(UserDetails.class));
        verify(userRepository, never()).save(any(Users.class));
    }
}