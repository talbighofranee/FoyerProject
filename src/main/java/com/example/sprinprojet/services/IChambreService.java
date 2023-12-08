package com.example.sprinprojet.services;


import com.example.sprinprojet.entity.Chambre;
import com.example.sprinprojet.entity.TypeChambre;
import com.google.zxing.WriterException;

import java.io.IOException;
import java.util.List;

public interface IChambreService {
  List<Chambre> retrieveAllChambres();

  Chambre addChambre(Chambre ch);

  Chambre updateChambre(Chambre ch);

  Chambre retrieveChambre(Long idChambre);

  void removeChambre(Long idChambre);

  void pourcentageChambreParTypeChambre();

  byte[] generateQRCode(Chambre chambre) throws IOException, WriterException;

  void sendEmail(String to, String subject, String body);

  Chambre affecterChambreABloc(Long chambreId, Long blocId);

  List<Chambre> getChambresParNomBloc(String nomBloc);

  long nbChambreParTypeEtBloc(TypeChambre type, long idBloc);
}
