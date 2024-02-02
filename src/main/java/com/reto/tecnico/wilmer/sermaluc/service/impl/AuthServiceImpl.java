package com.reto.tecnico.wilmer.sermaluc.service.impl;

import com.reto.tecnico.wilmer.sermaluc.exceptions.CustomException;
import com.reto.tecnico.wilmer.sermaluc.model.Users;
import com.reto.tecnico.wilmer.sermaluc.model.request.AuthRequest;
import com.reto.tecnico.wilmer.sermaluc.model.response.AuthResponse;
import com.reto.tecnico.wilmer.sermaluc.repository.IUserRepository;
import com.reto.tecnico.wilmer.sermaluc.security.services.JwtUtilService;
import com.reto.tecnico.wilmer.sermaluc.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthServiceImpl implements IAuthService {

  @Autowired
  IUserRepository userRepository;

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserDetailsService userDetailsService;

  @Autowired
  JwtUtilService jwtUtilService;

  @Override
  public AuthResponse authenticate (AuthRequest authRequest) {

    Optional<Users> optionalUsers = userRepository.findByEmail(authRequest.getEmail());

    if(!optionalUsers.isPresent()) {
      throw new CustomException("Credenciales inválidas.");
    }

    Users users = optionalUsers.get();

    if(!users.getPassword().equals(authRequest.getPassword())) {
      throw new CustomException("Credenciales inválidas.");
    }

    if(!users.getIsActive()) {
      throw new CustomException("El usuario se encuentra inactivo, contacta con el administrador.");
    }

    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
    UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getEmail());
    AuthResponse authResponse = jwtUtilService.generateToken(userDetails);

    users.setLast_login(LocalDateTime.now());
    userRepository.save(users);
    authResponse.setName(users.getName());
    authResponse.setEmail(users.getEmail());

    return authResponse;
  }
}
