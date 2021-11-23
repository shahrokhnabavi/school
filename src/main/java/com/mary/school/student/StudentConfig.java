package com.mary.school.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner command(StudentRepository repository) {
        List<Student> list = Arrays.asList(
            new Student( "Maryam", "mary@gmail.com", LocalDate.of(1984, Month.FEBRUARY, 6)),
            new Student( "Shahrokh", "sha@gmail.com", LocalDate.of(1983, Month.APRIL, 10))
        );

        return args -> {
            repository.saveAll(
                list
            );
        };
    }
}
