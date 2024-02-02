package com.reto.tecnico.wilmer.sermaluc.service;

import com.reto.tecnico.wilmer.sermaluc.model.request.AuthRequest;
import com.reto.tecnico.wilmer.sermaluc.model.response.AuthResponse;

public interface IAuthService {

  AuthResponse authenticate(AuthRequest authRequest);
}
