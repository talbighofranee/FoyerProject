package com.example.sprinprojet.contollers;


import com.example.sprinprojet.entity.Bloc;
import com.example.sprinprojet.entity.Etudiant;
import com.example.sprinprojet.entity.Foyer;

import com.example.sprinprojet.services.FoyerServiceImp;
import com.example.sprinprojet.services.IFoyerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@RestController

@RequestMapping("/foyer")
public class FoyerController {
   FoyerServiceImp FoyerService;

   @GetMapping("/getall")

   public List<Foyer>getFoyerList(){
      List<Foyer>foyerList=FoyerService.retrieveAllFoyers();
      return foyerList;
   }


   @PostMapping("/add-Foyer")
   public Foyer addFoyer(@RequestBody Foyer f) {
      Foyer foyer = FoyerService.addFoyer(f);
      return foyer;
   }
   @GetMapping("/retrieve-Foyer/{Foyer-id}")
   public Foyer retrieveFoyer(@PathVariable("Foyer-id") Long idFoyer) {

      return FoyerService.retrieveFoyer(idFoyer);
   }
   @DeleteMapping("/remove-Foyer/{Foyer-id}")
   public void removeFoyer(@PathVariable("Foyer-id") Long idFoyer) {
      FoyerService.removeFoyer(idFoyer);
   }
   @PutMapping("/update-Foyer")
   public Foyer updateFoyer(@RequestBody Foyer f) {
      Foyer foyer= FoyerService.updateFoyer(f);
      return foyer;
   }
  @PutMapping("/archiver_foyer/{foyer-id}")
   public void archiverFoyer(@PathVariable("foyer-id") Long idFoyer){
     FoyerService.archiverFoyer(idFoyer);



   }
    @PostMapping("/addfoyerbloc")
    public Foyer ajouterfoyeravecbloc (@RequestBody Foyer f) {
        Foyer foyer = FoyerService.addFoyerWithBloc(f);
        return foyer;
    }

    }
