package com.example.sprinprojet.repository;

import com.example.sprinprojet.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@Repository
public interface ReservationRepository extends JpaRepository<Reservation,String> {
    List<Reservation> findByAnneeUniversitaireBetween(Date b, Date f);

    List<Reservation> findByChambreNumeroChambreAndEstValideAndAnneeUniversitaireBetween(long cc, Boolean valide , LocalDate dateS , LocalDate dateE);

    @Query("SELECT r FROM Reservation r WHERE r.status = 'NON_CONFIRMEE' AND r.dateCreation < :cutoffDate")
    List<Reservation> findUnconfirmedReservationsOlderThan(@Param("cutoffDate") Date cutoffDate);
    @Modifying
    @Query("UPDATE Reservation r SET r.status = 'ANNULÉE' WHERE r.estValide = false AND r.dateCreation < :cutoffDate")
    void cancelUnconfirmedReservations(@Param("cutoffDate") Date cutoffDate);
}
