package com.example.sprinprojet.repository;

import com.example.sprinprojet.entity.Chambre;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChambreRepository extends JpaRepository<Chambre,Long> {

}
