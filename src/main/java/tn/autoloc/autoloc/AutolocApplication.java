package tn.autoloc.autoloc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import tn.autoloc.autoloc.service.IVehiculeService;

@SpringBootApplication
@EnableScheduling
@EnableAspectJAutoProxy
public class AutolocApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutolocApplication.class, args);
    }

    @Bean
    public CommandLineRunner demoRunner(IVehiculeService vehiculeService) {
        return args -> {
            // Démonstration des aspects AOP et Scheduler
            try {
                // 1. Test Logging Aspect et Performance Aspect (méthode normale avec paramètres)
                vehiculeService.afficherVehiculeById(1L);

                // 2. Test Exception Handling Aspect (ID introuvable)
                try {
                    vehiculeService.affecterEquipement(999L, 999L);
                } catch (Exception ignored) {
                    // L'aspect ExceptionHandlingAspect intercepte l'exception avant ce bloc
                }

                // 3. Test Performance Aspect avec dépassement de 1000 ms
                vehiculeService.testerMethodeLente();

            } catch (Exception e) {
                // Ignoré pour la suite du démarrage
            }
        };
    }
}
