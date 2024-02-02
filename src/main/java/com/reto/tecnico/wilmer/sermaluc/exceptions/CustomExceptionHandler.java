package com.reto.tecnico.wilmer.sermaluc.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;

@RestControllerAdvice
public class CustomExceptionHandler {

  @ExceptionHandler(CustomException.class)
  public ResponseEntity<Object> handleFeignException(CustomException ex) {
    HttpStatus status = HttpStatus.BAD_REQUEST;
    LinkedHashMap<String, Object> response = new LinkedHashMap<>();
    response.put("message", ex.getMessage());
    return ResponseEntity.status(status).body(response);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Object> handleFeignException(MethodArgumentNotValidException ex) {
    HttpStatus status = HttpStatus.BAD_REQUEST;
    LinkedHashMap<String, Object> response = new LinkedHashMap<>();
    response.put("message", "Los datos enviados no son válidos");
    return ResponseEntity.status(status).body(response);
  }
}
