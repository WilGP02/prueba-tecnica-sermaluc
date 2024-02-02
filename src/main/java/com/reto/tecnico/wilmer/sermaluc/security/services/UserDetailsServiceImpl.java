package com.reto.tecnico.wilmer.sermaluc.security.services;

import com.reto.tecnico.wilmer.sermaluc.model.Users;
import com.reto.tecnico.wilmer.sermaluc.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  IUserService userService;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Users user = getUserDetails(email);

    if (user == null) {
      throw new UsernameNotFoundException(email);
    }
    return User
            .withUsername(email)
            .password(user.getPassword())
            .build();
  }

  private Users getUserDetails(String email) {
    Users user = null;
    try {
      user = userService.getUserByEmail(email);
    } catch (Exception error) {
      throw new UsernameNotFoundException("Usuario "+ email + " no encontrado");
    }

    return List.of(user)
            .stream()
            .filter(e -> e != null && e.getEmail().equals(email))
            .findFirst()
            .orElse(null);
  }
}
