package com.reto.tecnico.wilmer.sermaluc.exceptions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomExceptionHandlerTest {

    @Mock
    private CustomException customException;

    @Mock
    private MethodArgumentNotValidException methodArgumentNotValidException;

    @InjectMocks
    private CustomExceptionHandler customExceptionHandler;

    @Test
    public void testHandleCustomException() {
        String errorMessage = "Custom exception message";
        when(customException.getMessage()).thenReturn(errorMessage);

        ResponseEntity<Object> responseEntity = customExceptionHandler.handleFeignException(customException);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        LinkedHashMap<String, Object> responseBody = (LinkedHashMap<String, Object>) responseEntity.getBody();
        assertEquals(errorMessage, responseBody.get("message"));
    }

    @Test
    public void testHandleMethodArgumentNotValidException() {
        String expectedMessage = "Los datos enviados no son válidos";

        ResponseEntity<Object> responseEntity = customExceptionHandler.handleFeignException(methodArgumentNotValidException);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        LinkedHashMap<String, Object> responseBody = (LinkedHashMap<String, Object>) responseEntity.getBody();
        assertEquals(expectedMessage, responseBody.get("message"));
    }
}