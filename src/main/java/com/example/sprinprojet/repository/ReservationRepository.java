package com.example.sprinprojet.repository;

import com.example.sprinprojet.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation,String> {
}
