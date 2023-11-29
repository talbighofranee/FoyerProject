package com.example.sprinprojet.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table( name = "Etudiant")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Etudiant implements Serializable {
    @Id

    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="idEtudiant")
    private Long idEtudiant; // Clé primaire


    private String nomEt;

    private String prenomEt;

    private Long cin;

    private String ecole;
    private String StudentEmail;

    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
@JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Reservation> reservations; //etudiant parent

}
