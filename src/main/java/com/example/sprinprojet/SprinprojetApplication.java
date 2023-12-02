package com.example.sprinprojet;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling

public class SprinprojetApplication {

    public static void main(String[] args) {
        SpringApplication.run(SprinprojetApplication.class, args);
    }
  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }

}
