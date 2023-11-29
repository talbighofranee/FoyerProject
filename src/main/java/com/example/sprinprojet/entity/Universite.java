package com.example.sprinprojet.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name="Universite")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Universite implements Serializable {
   @Id

   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="idUniversite")
   private Long idUniversite; // Clé primaire

    private String nom;

    private String adresse ;

@OneToOne(mappedBy="universite")
@JsonIgnore
    private Foyer foyer;


    public void setFoyUni(Foyer foyer) {
    }
}
