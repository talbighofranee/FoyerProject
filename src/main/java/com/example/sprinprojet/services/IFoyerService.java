package com.example.sprinprojet.services;



import com.example.sprinprojet.entity.Foyer;

import java.util.List;

public interface IFoyerService {
    List<Foyer> retrieveAllFoyers();

    Foyer addFoyer(Foyer f);

    Foyer updateFoyer(Foyer f);

    Foyer retrieveFoyer(Long idFoyer);

    void removeFoyer(Long idFoyer);
    void archiverFoyer (Long idFoyer);
    Foyer addFoyerWithBloc (Foyer foyer);


}
