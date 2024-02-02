package com.reto.tecnico.wilmer.sermaluc.controller;

import com.reto.tecnico.wilmer.sermaluc.model.Users;
import com.reto.tecnico.wilmer.sermaluc.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user")
public class UserController {

  @Autowired
  private IUserService userService;

  @PostMapping("/register")
  @Operation(
          summary = "Registro de Usuarios",
          description = "Permite el registro de usuarios.",
          parameters = {
                  @Parameter(
                          name = "Authorization",
                          description = "Token JWT de acceso",
                          required = true,
                          schema = @Schema(type = "string", format = "token"),
                          in = ParameterIn.HEADER
                  )
          },
          responses = {
                  @ApiResponse(responseCode = "200", description = "Recurso obtenido con éxito"),
                  @ApiResponse(responseCode = "401", description = "No autorizado", content = @Content)
          }
  )
  public ResponseEntity<?> registerUser(@RequestBody @Valid Users user) {
    return ResponseEntity.ok(userService.registerUser(user));
  }

  @PutMapping("/update/state")
  @Operation(
          summary = "Habilitar/Deshabilitar Usuarios",
          description = "Permite el cambio de estado de los usuarios.",
          parameters = {
                  @Parameter(
                          name = "Authorization",
                          description = "Token JWT de acceso",
                          required = true,
                          schema = @Schema(type = "string", format = "token"),
                          in = ParameterIn.HEADER
                  )
          },
          responses = {
                  @ApiResponse(responseCode = "200", description = "Recurso obtenido con éxito"),
                  @ApiResponse(responseCode = "401", description = "No autorizado", content = @Content)
          }
  )
  public ResponseEntity<?> updateState(@RequestParam String email, @RequestParam Boolean state) {
    return ResponseEntity.ok(userService.updateUser(email, state));
  }

  @GetMapping
  @Operation(
          summary = "Obtener el Usuario por Email",
          description = "Permite obtener el usuario con el correo electrónico.",
          parameters = {
                  @Parameter(
                          name = "Authorization",
                          description = "Token JWT de acceso",
                          required = true,
                          schema = @Schema(type = "string", format = "token"),
                          in = ParameterIn.HEADER
                  )
          },
          responses = {
                  @ApiResponse(responseCode = "200", description = "Recurso obtenido con éxito"),
                  @ApiResponse(responseCode = "401", description = "No autorizado", content = @Content)
          }
  )
  public ResponseEntity<?> getUser(@RequestParam String email) {
    return ResponseEntity.ok(userService.getUserByEmail(email));
  }

}