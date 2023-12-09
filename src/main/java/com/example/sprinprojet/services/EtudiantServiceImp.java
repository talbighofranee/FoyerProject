package com.example.sprinprojet.services;

import com.example.sprinprojet.entity.Etudiant;
import com.example.sprinprojet.entity.Reservation;
import com.example.sprinprojet.entity.Status;
import com.example.sprinprojet.repository.EtudiantRepository;
import com.example.sprinprojet.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.sound.midi.MidiSystem;
import java.util.*;

@Service
@AllArgsConstructor
public class EtudiantServiceImp implements IEtudiantService{
    EtudiantRepository etudiantRepository ;
    ReservationRepository reservationRepository;



    @Override
    public List<Etudiant> retrieveAllEtudiants() {
        return etudiantRepository.findAll();
    }

    @Override
    public Etudiant addEtudiant(Etudiant e) {
        return etudiantRepository.save(e);
    }

    @Override
    public Etudiant updateEtudiant(Etudiant e) {
        return etudiantRepository.save(e);
    }

    @Override
    public Etudiant retrieveEtudiant(Long idEtudiant) {
        return etudiantRepository.findById(idEtudiant).get();
    }

    @Override
    public void removeEtudiant(Long idEtudiant) {
        etudiantRepository.deleteById(idEtudiant);

    }

    @Override
    public Etudiant affecterEtudiantAReservation(String nomEt, String prenomEt, String idReservation) {
        Etudiant etudiant = etudiantRepository.findByNomEtAndPrenomEt(nomEt, prenomEt);

        // Find the Reservation by ID
        Reservation reservation = reservationRepository.findById(idReservation).get();




        List<Reservation> reservations =  new ArrayList<>() ;
        reservations=etudiant.getReservations();
        reservations.add(reservation);

        etudiant.setReservations(reservations);
        // Update the Reservation
        etudiantRepository.save(etudiant);
        return etudiant;
    }



    @Override
    public List<Etudiant> addEtudiants(List<Etudiant> etudiants) {
        return etudiantRepository.saveAll(etudiants);
    }

    public Etudiant updateEtudiantAndReservations(Long idEtudiant, Etudiant updatedEtudiant) {
        Etudiant existingEtudiant = etudiantRepository.findById(idEtudiant).orElse(null);

        if (existingEtudiant != null) {
            existingEtudiant.setNomEt(updatedEtudiant.getNomEt());
            existingEtudiant.setPrenomEt(updatedEtudiant.getPrenomEt());
            existingEtudiant.setStudentEmail(updatedEtudiant.getStudentEmail());
            existingEtudiant.setCin(updatedEtudiant.getCin());
            existingEtudiant.setEcole(updatedEtudiant.getEcole());
            existingEtudiant.setDateNaissance(updatedEtudiant.getDateNaissance());


            List<Reservation> existingReservations = existingEtudiant.getReservations();



            // Mettez à jour l'entité avec la liste modifiée des réservations
            existingEtudiant.setReservations(existingReservations);

            return etudiantRepository.save(existingEtudiant);
        } else {
            return null;
        }
    }
    public List<Reservation> getReservationsForEtudiant(Long etudiantId) {
        Optional<Etudiant> etudiantOptional = etudiantRepository.findById(etudiantId);
        return etudiantOptional.map(Etudiant::getReservations).orElse(Collections.emptyList());
    }

}
