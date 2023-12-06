package com.example.sprinprojet.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table( name = "User")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idUser")

    private Long idUser; // Clé primaire

    private String nomUser;

    private String prenomUser;

    private String email;

    private String motdepasse;

    @Enumerated(EnumType.STRING)
    private UserRole role;
}
