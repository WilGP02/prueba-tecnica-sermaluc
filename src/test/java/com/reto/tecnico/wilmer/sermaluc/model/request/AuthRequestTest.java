package com.reto.tecnico.wilmer.sermaluc.model.request;

import org.junit.jupiter.api.Test;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import static org.junit.jupiter.api.Assertions.*;

public class AuthRequestTest {

    private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = validatorFactory.getValidator();

    @Test
    public void testValidEmail() {
        AuthRequest authRequest = new AuthRequest();
        authRequest.setEmail("test@example.com");
        authRequest.setPassword("Elmaestro1$");

        assertEquals(0, validator.validate(authRequest).size());
    }

    @Test
    public void testInvalidEmail() {
        AuthRequest authRequest = new AuthRequest();
        authRequest.setEmail("invalid_email");
        authRequest.setPassword("Elmaestro1$");

        assertEquals(1, validator.validate(authRequest).size());
    }

    @Test
    public void testValidPassword() {
        AuthRequest authRequest = new AuthRequest();
        authRequest.setEmail("test@example.com");
        authRequest.setPassword("Elmaestro1$");

        assertEquals(0, validator.validate(authRequest).size());
    }

    @Test
    public void testInvalidPassword() {
        AuthRequest authRequest = new AuthRequest();
        authRequest.setEmail("test@example.com");
        authRequest.setPassword("invalidpassword");

        assertEquals(1, validator.validate(authRequest).size());
    }

    @Test
    public void testEmptyPassword() {
        AuthRequest authRequest = new AuthRequest();
        authRequest.setEmail("test@example.com");
        authRequest.setPassword("");

        assertEquals(1, validator.validate(authRequest).size());
    }
}