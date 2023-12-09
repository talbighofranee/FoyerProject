package com.example.sprinprojet.services;

import com.example.sprinprojet.entity.Etudiant;
import com.example.sprinprojet.entity.Reservation;
import com.example.sprinprojet.entity.Status;
import com.example.sprinprojet.repository.ReservationRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReservationServiceImp implements IReservationService {
    ReservationRepository reservationRepository;

    @Override
    public List<Reservation> retrieveAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation addReservation(Reservation r) {
        return reservationRepository.save(r);
    }

    @Override
    public Reservation updateReservation(Reservation r) {
        return reservationRepository.save(r);
    }

    @Override
    public Reservation retrieveReservation(String idReservation) {
        return reservationRepository.findById(idReservation).get();
    }

    @Override
    public void removeReservation(String idReservation) {
        reservationRepository.deleteById(idReservation);

    }
    public List<Reservation> getReservationParAnneeUniversitaire(Date dateDebut, Date dateFin )
    {

        List<Reservation> allReservations = reservationRepository.findByAnneeUniversitaireBetween(dateDebut,dateFin);
        return allReservations;

    }
    public boolean updateReservationStatus(String reservationId, Status newStatus) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(reservationId);

        if (reservationOptional.isPresent()) {
            Reservation reservation = reservationOptional.get();
            reservation.setStatus(newStatus);
            reservationRepository.save(reservation);
            return true;
        }

        return false;
    }
    @Transactional
    //@Scheduled(fixedRate = 60000)
    //@Scheduled(cron = "0 0 0 * * *")
    public void cancelUnconfirmedReservations() {
        LocalDateTime cutoffDate = LocalDateTime.now().minusHours(1);
        Date cutoffDateAsDate = Date.from(cutoffDate.atZone(ZoneId.systemDefault()).toInstant());
        List<Reservation> unconfirmedReservations = reservationRepository.findUnconfirmedReservationsOlderThan(cutoffDateAsDate);

        for (Reservation reservation : unconfirmedReservations) {

            reservation.setStatus(Status.Annulee);
            reservationRepository.save(reservation);
        }
    }
    @Autowired
    private EmailService emailService;

    public String getStudentEmail(String reservationId) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(reservationId);
        if (reservationOptional.isPresent()) {
            Reservation reservation = reservationOptional.get();
            List<Etudiant> etudiants = reservation.getEtudiants();
            Etudiant etudiantLie = etudiants.stream()
                    .filter(etudiant -> etudiant.getReservations().contains(reservation))
                    .findFirst()
                    .orElse(null);

            if (etudiantLie != null) {
                return etudiantLie.getStudentEmail();
            }
        }
        return null;
    }


    public void confirmReservation(String id) {
        Reservation reservation = reservationRepository.findById(id).orElse(null);

        if (reservation != null && reservation.getStatus() == Status.NON_CONFIRMEE) {


            reservation.setStatus(Status.Confirmee);
            reservationRepository.save(reservation);
            List<Etudiant> etudiants = reservation.getEtudiants();

            for (Etudiant etudiant : etudiants) {
                String studentEmail = etudiant.getStudentEmail();
                if (studentEmail != null) {
                    String subject = "Confirmation de réservation";
                    String messageText = "Votre réservation a été confirmée avec succès.";
                    emailService.sendConfirmationEmail(studentEmail, subject, messageText);
                }
            }}
    }

}
