package com.example.sprinprojet.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jdk.jshell.Snippet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table( name = "Reservation")
public class Reservation implements Serializable {
    @Id

    @Column(name="idReservation")

    private String idReservation; // Clé primaire


    private boolean estValide=false;


    @Temporal(TemporalType.DATE)
    private Date anneeUniversitaire;

    @Temporal(TemporalType.DATE)
    private Date dateCreation;
    @Enumerated(EnumType.STRING)
    private Status status;
    @JsonIgnore
    @ManyToMany(mappedBy="reservations",cascade = CascadeType.ALL)
    private List<Etudiant> etudiants;

    public void setEtudiantsReservation(Etudiant etudiant) {
    }
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private Chambre chambre;

}
