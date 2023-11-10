package com.example.sprinprojet.services;

import com.example.sprinprojet.entity.Universite;

import java.util.List;

public interface IUniversiteService {
    List<Universite> retrieveAllUniversites();

    Universite addUniversite(Universite u);

    Universite updateUniversite(Universite u);

    Universite retrieveUniversite(Long idUniversite);

    void removeUniversite(Long idUniversite);
     Universite affecterFoyerAUniversite (long idFoyer, String nom) ;
  Universite desaffecterFoyerAUniversite (long idFoyer);
}
