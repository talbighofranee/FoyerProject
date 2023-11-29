package com.example.sprinprojet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendConfirmationEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
      message.setFrom("talbi.ghofrane@esprit.tn");
        message.setTo("talbighofrane20@gmail.com");
       message.setSubject("email de confirmation de reservation");
        message.setText("Vous avez recu un nouveaux message "
               );

        javaMailSender.send(message);
    }
}



