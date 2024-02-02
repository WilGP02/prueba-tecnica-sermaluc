package com.reto.tecnico.wilmer.sermaluc.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@MappedSuperclass
public class BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  private LocalDateTime created;

  private LocalDateTime modified;

  private LocalDateTime last_login;

  @PrePersist
  private void prePersist() {
    created = LocalDateTime.now();
    modified = LocalDateTime.now();
    last_login = LocalDateTime.now();
  }

}
