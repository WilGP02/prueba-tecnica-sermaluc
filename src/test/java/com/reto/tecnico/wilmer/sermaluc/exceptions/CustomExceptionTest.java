package com.reto.tecnico.wilmer.sermaluc.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomExceptionTest {

    @Test
    public void testCustomExceptionWithMessage() {
        String message = "Test message";
        CustomException exception = new CustomException(message);
        assertEquals(message, exception.getMessage());
    }
}