package com.example.demo.Student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args ->{
             Student par = new Student(
                    "Par",
                    "email@email.com",
                    LocalDate.of(1990,05,25)


            );

             Student alex = new Student(
                     "alex",
                     "alex@email.com",
                     LocalDate.of(1990,05,25)


             );
             repository.saveAll(
                     List.of(par, alex)
             );
        };

    }
}
