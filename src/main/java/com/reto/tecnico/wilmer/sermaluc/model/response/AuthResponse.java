package com.reto.tecnico.wilmer.sermaluc.model.response;

import lombok.Data;

@Data
public class AuthResponse {
  private String name;
  private String email;
  private String access_token;
  private long expires_in;
  private String token_type;
}
