package com.reto.tecnico.wilmer.sermaluc.service.impl;

import com.reto.tecnico.wilmer.sermaluc.exceptions.CustomException;
import com.reto.tecnico.wilmer.sermaluc.model.Users;
import com.reto.tecnico.wilmer.sermaluc.repository.IUserRepository;
import com.reto.tecnico.wilmer.sermaluc.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public Users registerUser(Users user) {
        Optional<Users> optionalUsers = userRepository.findByEmail(user.getEmail());
        if (optionalUsers.isPresent()) {
            throw new CustomException("El correo ya está registrado");
        }

        return userRepository.save(user);
    }

    @Override
    public Users updateUser (String email, Boolean state) {
        Optional<Users> optionalUsers = userRepository.findByEmail(email);
        if (!optionalUsers.isPresent()) {
            throw new CustomException("El correo no se encuentra registrado");
        }
        Users updateUser = optionalUsers.get();

        return userRepository.save(updateUser);
    }

    @Override
    public Users getUserByEmail (String email) {
        Optional<Users> optionalUsers = userRepository.findByEmail(email);
        if (!optionalUsers.isPresent()) {
            throw new CustomException("El correo no se encuentra registrado");
        }
        return optionalUsers.get();
    }

}