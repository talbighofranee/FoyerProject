package com.example.sprinprojet.contollers;

import com.example.sprinprojet.entity.Chambre;

import com.example.sprinprojet.entity.TypeChambre;
import com.example.sprinprojet.repository.ChambreRepository;
import com.example.sprinprojet.services.IChambreService;
import com.google.zxing.WriterException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/chambre")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class ChambreController {
    IChambreService iChambreService;
    ChambreRepository chambreRepository;

    @GetMapping("/retrieve-all-chambre")
    public List<Chambre> getChambreList(){
        List<Chambre>chambreList=iChambreService.retrieveAllChambres();
        return chambreList;
    }

    @PostMapping("/add-Chambre")
    public Chambre addChambre(@RequestBody Chambre ch) {
        Chambre chambre = iChambreService.addChambre(ch);
        return chambre;
    }
    @GetMapping("/retrieve-Chambre/{Chambre-id}")
    public Chambre retrieveChambre(@PathVariable("Chambre-id") Long chambreId) {
        return iChambreService.retrieveChambre(chambreId);
    }
    @DeleteMapping("/remove-Chambre/{Chambre-id}")
    public void removeChambre(@PathVariable("Chambre-id") Long idChambre) {
        iChambreService.removeChambre(idChambre);
    }
    @PutMapping("/update-Chambre")
    public Chambre updateEtudiant(@RequestBody Chambre ch) {
        Chambre chambre= iChambreService.updateChambre(ch);
        return chambre;
    }
  @PutMapping("/update-chambre/{id}")
  public Chambre updateChambreByID(@RequestBody Chambre updatedChambre, @PathVariable("id") Long idCahmbre) {
    Chambre existingChambre = chambreRepository.findById(idCahmbre).orElse(null);

    if (existingChambre != null) {
      existingChambre.setNumeroChambre(updatedChambre.getNumeroChambre());
      existingChambre.setTypeC(updatedChambre.getTypeC());

      return chambreRepository.save(existingChambre);
    } else {

      return null;
    }
  }
  @GetMapping("/generate-qr/{chambre-id}")
  public ResponseEntity<byte[]> generateQRCode(@PathVariable("chambre-id") Long idChambre) {
    try {
      Chambre chambre = iChambreService.retrieveChambre(idChambre);

      if (chambre != null ) {
        byte[] qrCode = iChambreService.generateQRCode(chambre);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);

        return new ResponseEntity<>(qrCode, headers, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Or handle it in a way that makes sense for your use case
      }
    } catch (IOException e) {
      log.error("Erreur d'entrée/sortie lors de la génération du code QR : {}", e.getMessage());
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    } catch (WriterException e) {
      log.error("Erreur lors de la génération du code QR : {}", e.getMessage());
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    } catch (Exception e) {
      log.error("Une erreur inattendue s'est produite : {}", e.getMessage());
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  @GetMapping("/{nomBloc}")
  public List<Chambre> getChambresParNomBloc(@PathVariable String nomBloc) {
    List<Chambre> chambres = iChambreService.getChambresParNomBloc(nomBloc);
    return chambres;
  }
  @GetMapping("/nbChambreParTypeEtBloc/{typeChambre}/{idBloc}")
  public long nbChambreParTypeEtBloc(@PathVariable TypeChambre typeChambre , @PathVariable Long idBloc){
    return iChambreService.nbChambreParTypeEtBloc(typeChambre, idBloc);

  }

  @PostMapping("/affecter-chambre-a-bloc/{chambre-id}/{bloc-id}")
  public ResponseEntity<Chambre> affecterChambreABloc(
    @PathVariable("chambre-id") Long chambreId,
    @PathVariable("bloc-id") Long blocId) {

    Chambre chambre = iChambreService.affecterChambreABloc(chambreId, blocId);
    if (chambre != null) {
      return new ResponseEntity<>(chambre, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
