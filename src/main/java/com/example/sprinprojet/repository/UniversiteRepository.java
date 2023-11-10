package com.example.sprinprojet.repository;

import com.example.sprinprojet.entity.Universite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UniversiteRepository extends JpaRepository<Universite,Long> {
    Universite findByNom(String nom);

}
