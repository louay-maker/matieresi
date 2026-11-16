package tn.autoloc.autoloc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import tn.autoloc.autoloc.service.IVehiculeService;

@SpringBootApplication
@EnableScheduling
public class AutolocApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutolocApplication.class, args);
    }

    @Bean
    public CommandLineRunner demoRapport(IVehiculeService vehiculeService) {
        return args -> {
            // Déclenchement de démonstration pour afficher le rapport de la Question 3 dans la console
            vehiculeService.genererRapportVehicules();
        };
    }
}
