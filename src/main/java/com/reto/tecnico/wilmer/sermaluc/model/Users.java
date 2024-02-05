package com.reto.tecnico.wilmer.sermaluc.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Users extends BaseEntity {

  @Size(min = 1, max = 50)
  private String name;
  @Email
  private String email;
  @Pattern(regexp = "^(?=.*[A-Z])(?=.*[!@#$%^&*(),.?\\\":{}|<>])(?=.*[0-9]).{1,14}$")
  private String password;

  private Boolean isActive = Boolean.TRUE;

  @Valid
  @OneToMany(cascade = CascadeType.ALL)
  private List<Phone> phones;
}
