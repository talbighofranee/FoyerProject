package com.example.sprinprojet.services;


import com.example.sprinprojet.entity.Bloc;
import com.example.sprinprojet.entity.Chambre;
import com.example.sprinprojet.entity.Foyer;
import com.example.sprinprojet.repository.BlocRepository;
import com.example.sprinprojet.repository.FoyerRepository;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.sprinprojet.entity.Foyer.*;

@Service
@AllArgsConstructor
public class FoyerServiceImp  implements IFoyerService{
    FoyerRepository foyerRepository;
BlocRepository blocRepository;
    @Override
    public List<Foyer> retrieveAllFoyers() {

        return foyerRepository.findAll();
    }

    @Override
    public Foyer addFoyer(Foyer f) {

        return foyerRepository.save(f);
    }

    @Override
    public Foyer updateFoyer(Foyer f) {

        return foyerRepository.save(f);
    }

    @Override
    public Foyer retrieveFoyer(Long idFoyer) {

        return foyerRepository.findById(idFoyer).get();
    }

    @Override
    public void removeFoyer(Long idFoyer) {
        foyerRepository.deleteById(idFoyer);

    }



    @Override
    public void archiverFoyer(Long idFoyer) {
        foyerRepository.findById(idFoyer).get().setArchived(true);


    }


    @Override
    public Foyer addFoyerWithBloc(Foyer foyer) {
        //sauvegarder le fils
        Foyer foyer1 = foyerRepository.save(foyer);
        //parcourir les lists des parents
        foyer.getBlocs().stream().forEach(
                bloc -> {
                    bloc.setFoyer(foyer1);
                    blocRepository.save(bloc);
                }
        );
        return foyer;
        // !!!!  khtrna m child ll parent  !!!!
    }
}
