package com.example.sprinprojet.repository;

import com.example.sprinprojet.entity.Bloc;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlocRepository extends JpaRepository<Bloc,Long> {
    Bloc findByNomBloc(String nom);
  @Query("SELECT b.nomBloc, COUNT(c) FROM Bloc b JOIN b.chambres c GROUP BY b")
  List<Object[]> getStatistiquesChambresParBloc();
}
