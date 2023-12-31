package com.example.sprinprojet.services;

import com.example.sprinprojet.entity.Bloc;
import com.example.sprinprojet.entity.Chambre;
import com.example.sprinprojet.entity.Reservation;
import com.example.sprinprojet.entity.TypeChambre;
import com.example.sprinprojet.repository.BlocRepository;
import com.example.sprinprojet.repository.ChambreRepository;
import com.example.sprinprojet.repository.FoyerRepository;
import com.example.sprinprojet.repository.ReservationRepository;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;
import static java.time.temporal.TemporalAdjusters.lastDayOfYear;

@Service
@AllArgsConstructor
public class ChambreServiceImp implements IChambreService {
   ChambreRepository chambreRepository ;
   BlocRepository blocRepository;
   FoyerRepository foyerRepository;
   ReservationRepository reservationRepository;
  private final JavaMailSender javaMailSender;

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



    public Set<Chambre>getChambreParNomBloc(String nomb){
      Bloc b =blocRepository.findByNomBloc(nomb);
      Set<Chambre> chambre=b.getChambres();
      return chambre;
}
   /*@Override
   @Scheduled(fixedRate = 60000)
   public void pourcentageChambreParTypeChambre() {

       List<Chambre> chambreByType = chambreRepository.findAll();
      long nb = chambreByType.stream().count();
     long  countParTypeSIMPLE = chambreRepository.countChambresByTypeC(TypeChambre.SIMPLE);
      long countParTypeDOUBLE = chambreRepository.countChambresByTypeC(TypeChambre.DOUBLE);
      long countParTypeTRIPLE = chambreRepository.countChambresByTypeC(TypeChambre.TRIPLE);
      System.out.println("Le nombre total des chambres est :" +nb);
      System.out.println("Le pourcentage des chambres de type SIMPLE est :" +(countParTypeSIMPLE*100)/nb);
      System.out.println("Le pourcentage des chambres de type DOUBLE est :" +(countParTypeDOUBLE*100)/nb);
      System.out.println("Le pourcentage des chambres de type TRIPLE est :" +(countParTypeTRIPLE*100)/nb);


   }*/
  /* @Scheduled(fixedRate = 60000)
   void nbPlacesDispo(){
List<Chambre>ch =chambreRepository.findAll();
   int x=0;
   int currentYear= LocalDate.now().getYear();
   LocalDate instance =LocalDate.now().withYear(currentYear);
      LocalDate dateStart = instance.with(firstDayOfYear());
      LocalDate dateEnd = instance.with(lastDayOfYear());
      for (Chambre chambre : ch) {
         List<Reservation> availablePlaces = reservationRepository.findByChambreNumeroChambreAndEstValideAndAnneeUniversitaireBetween(chambre.getNumeroChambre(),true,dateStart,dateEnd );
         int nbplace =availablePlaces.size();
         if(chambre.getTypeC() == TypeChambre.SIMPLE)
            x = 1;
         if(chambre.getTypeC() == TypeChambre.DOUBLE)
            x = 2;
         if(chambre.getTypeC() == TypeChambre.TRIPLE)
            x = 3;
         if((x - nbplace) > 0)
         {
            System.out.println("Chambre ID: " + chambre.getIdChambre() +
                    ", Places disponibles: " + (x - nbplace)  +
                    " pour l'année en cours."+ currentYear);
         }
         else
            System.out.println("nombre de place est negative");

      }
   }*/

  public byte[] generateQRCode(Chambre chambre) throws IOException, WriterException {
    String chambreDetails = "Chambre: " + chambre.getNumeroChambre() ;

    Map<EncodeHintType, Object> hintMap = new HashMap<>();
    hintMap.put(EncodeHintType.ERROR_CORRECTION, "L");
    hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");

    QRCodeWriter qrCodeWriter = new QRCodeWriter();
    BitMatrix bitMatrix = qrCodeWriter.encode(chambreDetails, BarcodeFormat.QR_CODE, 300, 300, hintMap);

    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    MatrixToImageWriter.writeToStream(bitMatrix, "PNG", byteArrayOutputStream);

    return byteArrayOutputStream.toByteArray();
  }




  @Override
  @Scheduled(cron = "0 1 * * * *")
  public void pourcentageChambreParTypeChambre() {

    Map<TypeChambre, List<Chambre>> chambreByType = chambreRepository.findAll()
      .stream()
      .collect(Collectors.groupingBy(Chambre::getTypeC));


    long totalchbres = chambreRepository.count();
    chambreByType.forEach((type, chbres) -> {
      double pourcentage = (chbres.size() * 100.0) / totalchbres;
      System.out.printf("nbre total des chbres"+chambreByType.get(totalchbres)+"le pourcentage de chmbre par type Type chambre ", type, pourcentage);
    });
  }
  public List<Chambre> getChambresParNomBloc(String nomBloc) {
    // Use the BlocRepository to find the Bloc entity by nomBloc
    Bloc bloc = blocRepository.findByNomBloc(nomBloc);

    if (bloc != null) {
      // If the Bloc is found, retrieve the associated Chambres
      Set<Chambre> chambres = bloc.getChambres();
      return new ArrayList<>(chambres); // Convert the Set to a List
    } else {
      // Handle the case where no Bloc is found by the given nomBloc
      return Collections.emptyList(); // Return an empty list or handle the situation as needed
    }
  }

  public long nbChambreParTypeEtBloc(TypeChambre type, long idBloc) {
    return chambreRepository.countByTypeCAndBloc_IdBloc(type, idBloc);
  }





  @Override
  public void sendEmail(String to, String subject, String body) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(to);
    message.setSubject(subject);
    message.setText(body);
    javaMailSender.send(message);
  }
  public Chambre affecterChambreABloc(Long chambreId, Long blocId) {
    Chambre chambre = chambreRepository.findById(chambreId).orElse(null);
    Bloc bloc = blocRepository.findById(blocId).orElse(null);

    if (chambre != null && bloc != null) {
      chambre.setBloc(bloc);
      chambreRepository.save(chambre);
      String to = "emna.felfel@esprit.tn"; // replace with the recipient's email
      String subject = "Chambre Assigned to Bloc";
      String body = "Chambre with ID " + chambreId + " has been assigned to Bloc " + bloc.getNomBloc();

      sendEmail(to, subject, body);
    }
    return chambre;
  }
}
