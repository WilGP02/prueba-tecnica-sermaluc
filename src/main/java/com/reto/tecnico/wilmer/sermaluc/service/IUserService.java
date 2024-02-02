package com.reto.tecnico.wilmer.sermaluc.service;

import com.reto.tecnico.wilmer.sermaluc.model.Users;

public interface IUserService {
    Users registerUser(Users user);
    Users updateUser(String email, Boolean state);
    Users getUserByEmail(String email);
}