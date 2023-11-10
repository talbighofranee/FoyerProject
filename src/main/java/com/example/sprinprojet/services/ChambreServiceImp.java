package com.example.sprinprojet.services;

import com.example.sprinprojet.entity.Chambre;
import com.example.sprinprojet.repository.ChambreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ChambreServiceImp implements IChambreService {
   ChambreRepository chambreRepository ;

   @Override
   public List<Chambre> retrieveAllChambres() {
      return chambreRepository.findAll();
   }

   @Override
   public Chambre addChambre(Chambre ch) {
      return chambreRepository.save(ch);
   }

   @Override
   public Chambre updateChambre(Chambre ch) {
      return chambreRepository.save(ch);
   }

   @Override
   public Chambre retrieveChambre(Long idChambre) {
      return chambreRepository.findById(idChambre).get();
   }

   @Override
   public void removeChambre(Long idChambre) {
      chambreRepository.deleteById(idChambre);

   }
}
