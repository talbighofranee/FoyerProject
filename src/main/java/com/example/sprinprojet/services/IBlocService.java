package com.example.sprinprojet.services;

import com.example.sprinprojet.entity.Bloc;
import org.springframework.scheduling.annotation.Scheduled;


import java.util.List;

public interface IBlocService {
    List<Bloc> retrieveAllBlocs();

    Bloc addBloc(Bloc b);

    Bloc updateBloc(Bloc b);

    Bloc retrieveBloc(Long idBloc);

    void removeBloc(Long idBloc);
  Bloc affecterChambresABloc (List<Long> numeroChambre, String nomBloc) ;

  @Scheduled(fixedRate = 60000)
  void  listeChambresParBloc();

  void trierBlocsParNomBloc(List<Bloc> blocs);

}
