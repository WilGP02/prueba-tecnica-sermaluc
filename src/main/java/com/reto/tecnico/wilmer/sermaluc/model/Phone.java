package com.reto.tecnico.wilmer.sermaluc.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;

@Data
@Embeddable
@Entity
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Size(min = 1, max = 10)
    @Pattern(regexp = "^[0-9]{10}$")
    private String number;
    private String citycode;
    private String countrycode;

    // Métodos getter/setter
}