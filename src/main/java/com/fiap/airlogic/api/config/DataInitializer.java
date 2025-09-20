package com.fiap.airlogic.api.config;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import com.fiap.airlogic.api.repository.ReadingRepository;
import com.fiap.airlogic.api.model.Reading;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(ReadingRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                String[] sensores = {"Compressor1", "Atuador1", "Atuador2"};
                Random random = new Random();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

                for (String sensor : sensores) {
                    LocalDateTime data = LocalDateTime.of(2025, 5, 20, 0, 0);
                    for (int i = 0; i < 20; i++) {
                        double valor = 0 + (random.nextDouble() * 6.5);
                        LocalDateTime timestamp = data.plusMinutes(i * 60);
                        repository.save(new Reading(null, sensor, valor, timestamp));
                    }
                }
            }
        };
    }
}