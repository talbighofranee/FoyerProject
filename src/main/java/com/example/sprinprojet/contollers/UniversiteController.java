package com.example.sprinprojet.contollers;


import com.example.sprinprojet.entity.Universite;
import com.example.sprinprojet.services.IUniversiteService;

import com.example.sprinprojet.services.UniversiteServiceImpl;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor //pour l'instanciation de l'objet wa9et eli nji bch nesta3mlou bch on evite new
@RestController

@RequestMapping("/universite")
public class UniversiteController {

    UniversiteServiceImpl UniversiteService;// l'objet que je vais instancier
    @GetMapping("/retrieve-all-Universites")

    public List<Universite> getEtudiantList(){
        List<Universite>universiteList=UniversiteService.retrieveAllUniversites();
        return universiteList;
    }

    @PostMapping("/add-Universite")
    public Universite addEtudiant(@RequestBody Universite u) {
        Universite universite = UniversiteService.addUniversite(u);
        return universite;
    }
    @GetMapping("/retrieve-Universite/{Universite-id}")
    public Universite retrieveUniversite(@PathVariable("Universite-id") Long UniversiteId) {
        return UniversiteService.retrieveUniversite(UniversiteId);
    }
    @DeleteMapping("/remove-Universite/{Universite-id}")
    public void removeEtudiant(@PathVariable("Universite-id") Long idUniversite) {
        UniversiteService.removeUniversite(idUniversite);
    }
    @PutMapping("/update-Universite")
    public Universite updateEtudiant(@RequestBody Universite u) {
        Universite universite= UniversiteService.updateUniversite(u);
        return universite;
    }
    @PutMapping("/affecterFoyer/{idFoyer}/{nom}")
    public Universite affecterFoyerAUniversite(@PathVariable("idFoyer") Long idFoyer ,@PathVariable("nom") String nom){
       Universite universite=UniversiteService.affecterFoyerAUniversite(idFoyer,nom);
       return universite;


    }
}
