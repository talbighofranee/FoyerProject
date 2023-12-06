package com.example.sprinprojet.contollers;


import com.example.sprinprojet.entity.Reservation;
import com.example.sprinprojet.services.EmailService;
import com.example.sprinprojet.services.IReservationService;

import com.example.sprinprojet.services.ReservationServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {
    IReservationService iReservationService;
    ReservationServiceImp reservationServiceImp;
    EmailService emailService;

    @GetMapping("/retrieve-all-Reservations")

    public List<Reservation> getReservationList(){
        List<Reservation>reservationList=iReservationService.retrieveAllReservations();
        return reservationList;
    }

    @PostMapping("/add-Reservation")
    public Reservation addReservation(@RequestBody Reservation r) {
        Reservation reservation = iReservationService.addReservation(r);
        return reservation;
    }
    @GetMapping("/retrieve-Reservation/{Reservation-id}")
    public Reservation retrieveReservation(@PathVariable("Reservation-id") String reservationId) {
        return iReservationService.retrieveReservation(reservationId);
    }
    @DeleteMapping("/remove-Reservation/{Reservation-id}")
    public void removeReservation(@PathVariable("Reservation-id") String idReservation) {
        iReservationService.removeReservation(idReservation);
    }
    @PutMapping("/update-Reservation")
    public Reservation updateReservation(@RequestBody Reservation r) {
        Reservation reservation= iReservationService.updateReservation(r);
        return reservation;
    }
    @GetMapping("/getmail/{Reservation-id}")
    public String getMail(@PathVariable ("Reservation-id")String reservationid){
        return reservationServiceImp.getStudentEmail(reservationid);
    }
    @PostMapping("/confirm/{reservationId}")
    public ResponseEntity<String> confirmReservation(@PathVariable String reservationId) {
        // Confirmer la réservation
        reservationServiceImp.confirmReservation(reservationId);

        // Récupérer les e-mails des étudiants associés à la réservation
        String studentEmail = reservationServiceImp.getStudentEmail(reservationId);

        // Envoyer un e-mail de confirmation à chaque étudiant
        if (studentEmail != null) {
            // Envoyer un e-mail de confirmation à l'étudiant
            emailService.sendConfirmationEmail(studentEmail, "Confirmation de réservation", "Votre réservation a été confirmée avec succès.");

            return ResponseEntity.ok("Confirmation en cours. Un e-mail sera envoyé à l'étudiant.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la récupération de l'e-mail de l'étudiant.");
        }
    }
}
