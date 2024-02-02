package com.reto.tecnico.wilmer.sermaluc.repository;

import com.reto.tecnico.wilmer.sermaluc.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IUserRepository extends JpaRepository<Users, UUID> {
    Optional<Users> findByEmail(String email);
}