package com.example.sprinprojet.services;

import com.example.sprinprojet.entity.Foyer;
import com.example.sprinprojet.entity.Universite;
import com.example.sprinprojet.repository.FoyerRepository;
import com.example.sprinprojet.repository.UniversiteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UniversiteServiceImpl implements IUniversiteService {
    UniversiteRepository universiteRepository;
    FoyerRepository foyerRepository;

    @Override
    public List<Universite> retrieveAllUniversites() {
        return universiteRepository.findAll();
    }

    @Override
    public Universite addUniversite(Universite u) {
        return universiteRepository.save(u);
    }

    @Override
    public Universite updateUniversite(Universite u) {
        return universiteRepository.save(u);
    }

    @Override
    public Universite retrieveUniversite(Long idUniversite) {

        return universiteRepository.findById(idUniversite).get();
    }

    @Override
    public void removeUniversite(Long idUniversite) {
        universiteRepository.deleteById(idUniversite);

    }

    @Override
    public Universite affecterFoyerAUniversite(long idFoyer, String nom) {
        Foyer foyer =foyerRepository.findById(idFoyer).get();
        Universite universite =universiteRepository.findByNom(nom);
        universite.setFoyUni(foyer);
        universiteRepository.save(universite);
        return universite;
    }

    @Override
    public Universite desaffecterFoyerAUniversite(long idFoyer) {
        Foyer foyer=foyerRepository.findById(idFoyer).get();
        Universite universite = foyer.getUniversite();
        foyer.setUniversite(null);
        foyerRepository.save(foyer);
        return universite;

    }
}
