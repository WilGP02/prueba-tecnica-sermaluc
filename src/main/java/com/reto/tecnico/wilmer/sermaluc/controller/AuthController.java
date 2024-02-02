package com.reto.tecnico.wilmer.sermaluc.controller;

import com.reto.tecnico.wilmer.sermaluc.model.Users;
import com.reto.tecnico.wilmer.sermaluc.model.request.AuthRequest;
import com.reto.tecnico.wilmer.sermaluc.model.response.AuthResponse;
import com.reto.tecnico.wilmer.sermaluc.service.IAuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {

  @Autowired
  IAuthService authService;

  @PostMapping
  @Operation(
          summary = "Autenticación de usuarios",
          description = "Permite la autenticación de usuarios y generación de jwt.",
          responses = {
                  @ApiResponse(responseCode = "200", description = "Recurso obtenido con éxito")
          }
  )
  public ResponseEntity<?> authentication(@RequestBody @Valid AuthRequest authRequest) {
    return ResponseEntity.ok(authService.authenticate(authRequest));
  }

}
