package com.reto.tecnico.wilmer.sermaluc.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import com.reto.tecnico.wilmer.sermaluc.model.Users;

import java.util.UUID;

public class IUserServiceTest {

    private IUserService userService;
    private Users user;

    @BeforeEach
    public void setup() {
        userService = Mockito.mock(IUserService.class);
        user = new Users();
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("password");
    }

    @Test
    public void testRegisterUser_Success() {
        Users expectedUser = new Users();
        expectedUser.setId(UUID.randomUUID());
        expectedUser.setName("John Doe");
        expectedUser.setEmail("john.doe@example.com");
        expectedUser.setPassword("password");

        Mockito.when(userService.registerUser(user)).thenReturn(expectedUser);

        Users actualUser = userService.registerUser(user);

        Assertions.assertEquals(expectedUser.getId(), actualUser.getId());
        Assertions.assertEquals(expectedUser.getName(), actualUser.getName());
        Assertions.assertEquals(expectedUser.getEmail(), actualUser.getEmail());
        Assertions.assertEquals(expectedUser.getPassword(), actualUser.getPassword());
    }

    @Test
    public void testUpdateUser_Success() {
        String email = "john.doe@example.com";
        Boolean state = true;
        Users expectedUser = new Users();
        expectedUser.setId(UUID.randomUUID());
        expectedUser.setName("John Doe");
        expectedUser.setEmail("john.doe@example.com");
        expectedUser.setPassword("password");
        expectedUser.setIsActive(state);

        Mockito.when(userService.updateUser(email, state)).thenReturn(expectedUser);

        Users actualUser = userService.updateUser(email, state);

        Assertions.assertEquals(expectedUser.getId(), actualUser.getId());
        Assertions.assertEquals(expectedUser.getName(), actualUser.getName());
        Assertions.assertEquals(expectedUser.getEmail(), actualUser.getEmail());
        Assertions.assertEquals(expectedUser.getPassword(), actualUser.getPassword());
        Assertions.assertEquals(expectedUser.getIsActive(), actualUser.getIsActive());
    }

    @Test
    public void testGetUserByEmail_Success() {
        String email = "john.doe@example.com";
        Users expectedUser = new Users();
        expectedUser.setId(UUID.randomUUID());
        expectedUser.setName("John Doe");
        expectedUser.setEmail("john.doe@example.com");
        expectedUser.setPassword("password");

        Mockito.when(userService.getUserByEmail(email)).thenReturn(expectedUser);

        Users actualUser = userService.getUserByEmail(email);

        Assertions.assertEquals(expectedUser.getId(), actualUser.getId());
        Assertions.assertEquals(expectedUser.getName(), actualUser.getName());
        Assertions.assertEquals(expectedUser.getEmail(), actualUser.getEmail());
        Assertions.assertEquals(expectedUser.getPassword(), actualUser.getPassword());
    }
}