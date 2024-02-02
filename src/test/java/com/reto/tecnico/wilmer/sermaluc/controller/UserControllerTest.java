package com.reto.tecnico.wilmer.sermaluc.controller;

import com.reto.tecnico.wilmer.sermaluc.model.Users;
import com.reto.tecnico.wilmer.sermaluc.service.IUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserControllerTest {
    @InjectMocks
    private UserController userController;

    @Mock
    private IUserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerUser_ValidUser_ReturnsOkResponse() {
        // Arrange
        Users user = new Users();
        when(userService.registerUser(user)).thenReturn(user);

        // Act
        ResponseEntity<?> response = userController.registerUser(user);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
        verify(userService, times(1)).registerUser(user);
    }

    @Test
    void updateState_ValidEmailAndState_ReturnsOkResponse() {
        // Arrange
        String email = "test@example.com";
        Boolean state = true;
        Users user = new Users();
        when(userService.updateUser(email, state)).thenReturn(user);

        // Act
        ResponseEntity<?> response = userController.updateState(email, state);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
        verify(userService, times(1)).updateUser(email, state);
    }
}