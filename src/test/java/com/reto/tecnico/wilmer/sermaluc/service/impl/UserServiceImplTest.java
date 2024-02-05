package com.reto.tecnico.wilmer.sermaluc.service.impl;

import com.reto.tecnico.wilmer.sermaluc.exceptions.CustomException;
import com.reto.tecnico.wilmer.sermaluc.model.Users;
import com.reto.tecnico.wilmer.sermaluc.repository.IUserRepository;
import com.reto.tecnico.wilmer.sermaluc.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private IUserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerUserWithNewUserShouldReturnRegisteredUser() {
        // Arrange
        Users user = new Users();
        user.setEmail("wilmer_palomino@gmail.com");
        user.setLast_login(LocalDateTime.now());
        user.setModified(LocalDateTime.now());
        user.setCreated(LocalDateTime.now());

        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());
        when(userRepository.save(user)).thenReturn(user);

        // Act
        Users registeredUser = userService.registerUser(user);

        // Assert
        assertNotNull(registeredUser);
        assertEquals(user.getEmail(), registeredUser.getEmail());
        assertTrue(registeredUser.getIsActive());
        assertNotNull(registeredUser.getCreated());
        assertNotNull(registeredUser.getModified());
        assertNotNull(registeredUser.getLast_login());

        verify(userRepository, times(1)).findByEmail(user.getEmail());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void registerUserWithExistingUserShouldThrowCustomException() {
        // Arrange
        Users user = new Users();
        user.setEmail("wilmer_palomino@gmail.com");

        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        // Act and Assert
        assertThrows(CustomException.class, () -> userService.registerUser(user));

        verify(userRepository, times(1)).findByEmail(user.getEmail());
        verify(userRepository, never()).save(user);
    }

    @Test
    void updateUserWithExistingUserShouldReturnUpdatedUser() {
        // Arrange
        String email = "wilmer_palomino@gmail.com";
        Users user = new Users();
        user.setEmail(email);
        user.setIsActive(false);
        user.setModified(LocalDateTime.now());

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);

        // Act
        Users updatedUser = userService.updateUser(email, false);

        // Assert
        assertNotNull(updatedUser);
        assertEquals(email, updatedUser.getEmail());
        assertFalse(updatedUser.getIsActive());
        assertNotNull(updatedUser.getModified());

        verify(userRepository, times(1)).findByEmail(email);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void updateUserWithNonExistingUserShouldThrowCustomException() {
        // Arrange
        String email = "wilmer_palomino@gmail.com";

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(CustomException.class, () -> userService.updateUser(email, false));

        verify(userRepository, times(1)).findByEmail(email);
        verify(userRepository, never()).save(any());
    }

    @Test
    void getUserByEmailWithExistingUserShouldReturnUser() {
        // Arrange
        String email = "wilmer_palomino@gmail.com";
        Users user = new Users();
        user.setEmail(email);

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        // Act
        Users foundUser = userService.getUserByEmail(email);

        // Assert
        assertNotNull(foundUser);
        assertEquals(email, foundUser.getEmail());

        verify(userRepository, times(1)).findByEmail(email);
    }

    @Test
    void getUserByEmailWithNonExistingUserShouldThrowCustomException() {
        // Arrange
        String email = "wilmer_palomino@gmail.com";

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(CustomException.class, () -> userService.getUserByEmail(email));

        verify(userRepository, times(1)).findByEmail(email);
    }
}