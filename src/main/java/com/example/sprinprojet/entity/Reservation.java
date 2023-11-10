package com.example.sprinprojet.entity;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idReservation")

    private String idReservation; // Clé primaire


    private boolean estValide;


    @Temporal(TemporalType.DATE)
    private Date anneeUniveritaire;


    @ManyToMany(mappedBy="reservations",cascade = CascadeType.ALL)
    private List<Etudiant> etudiants;

    public void setEtudiantsReservation(Etudiant etudiant) {
    }



}
