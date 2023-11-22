package com.example.sprinprojet.repository;

import com.example.sprinprojet.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation,String> {
    List<Reservation> findByAnneeUniversitaireBetween(Date b, Date f);

    List<Reservation> findByChambreNumeroChambreAndEstValideAndAnneeUniversitaireBetween(long cc, Boolean valide , LocalDate dateS , LocalDate dateE);
}
