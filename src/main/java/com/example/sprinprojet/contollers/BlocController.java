package com.example.sprinprojet.contollers;

import com.example.sprinprojet.entity.Bloc;

import com.example.sprinprojet.repository.BlocRepository;
import com.example.sprinprojet.services.IBlocService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")


@RestController
@AllArgsConstructor
@RequestMapping("/Bloc")
@Tag(name = "Bloc API", description = "API for managing Bloc entities")

public class BlocController {
    IBlocService iBlocService  ; //on injecte l'interface car on peut faire appel à plusieurs interfaces
    BlocRepository blocRepository;
    @GetMapping("/retrieve-all-blocs")
    public List<Bloc> getBlocList(){
        List<Bloc>blocList=iBlocService.retrieveAllBlocs();
        return blocList;
    }

    @PostMapping("/add-bloc")
    public Bloc addBloc(@RequestBody Bloc b) {
       Bloc bloc = iBlocService.addBloc(b);
        return bloc;
    }
    @GetMapping("/retrieve-bloc/{bloc-id}")
    public Bloc retrieveBloc(@PathVariable("bloc-id") Long idBloc ) {

        return iBlocService.retrieveBloc( idBloc);
    }


    @DeleteMapping("/remove-bloc/{bloc-id}")
    public void removeBloc(@PathVariable("bloc-id") Long idBloc) {
        iBlocService.removeBloc(idBloc);
    }


    @PutMapping("/update-bloc")
    public Bloc updateBloc(@RequestBody Bloc b) {
        Bloc bloc= iBlocService.updateBloc(b);
        return bloc;
    }
  @Operation(summary = "Update Bloc", description = "Update an existing Bloc entity.")

  @PutMapping("/update-bloc/{id}")
  public Bloc updateBlocByID(@RequestBody Bloc updatedBloc, @PathVariable("id") Long idBloc) {
    Bloc existingBloc = blocRepository.findById(idBloc).orElse(null);

    if (existingBloc != null) {
      existingBloc.setNomBloc(updatedBloc.getNomBloc());
      existingBloc.setCapacitebloc(updatedBloc.getCapacitebloc());
      // Si besoin de mettre à jour d'autres attributs, faites de même

      return blocRepository.save(existingBloc);
    } else {
      // Gérer le cas où le bloc avec l'ID donné n'est pas trouvé
      // Vous pouvez choisir de renvoyer une erreur, une réponse HTTP appropriée, ou autre chose.
      return null;
    }
  }


}
