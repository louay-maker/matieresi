package tn.autoloc.autoloc.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.autoloc.autoloc.entity.Equipement;
import tn.autoloc.autoloc.entity.Vehicule;
import tn.autoloc.autoloc.repository.EquipementRepository;
import tn.autoloc.autoloc.repository.VehiculeRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class VehiculeServiceImpl implements IVehiculeService {

    private final VehiculeRepository vehiculeRepository;
    private final EquipementRepository equipementRepository;

    @Override
    public List<Vehicule> afficherTousVehicules() {
        return vehiculeRepository.findAll();
    }

    @Override
    public Vehicule ajouterVehicule(Vehicule vehicule) {
        return vehiculeRepository.save(vehicule);
    }

    @Override
    public Vehicule modifierVehicule(Vehicule vehicule) {
        return vehiculeRepository.save(vehicule);
    }

    @Override
    public Vehicule afficherVehiculeById(Long id) {
        return vehiculeRepository.findById(id).orElse(null);
    }

    @Override
    public void supprimerVehicule(Long id) {
        vehiculeRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Vehicule affecterEquipement(Long idVehicule, Long idEquipement) {
        Vehicule vehicule = vehiculeRepository.findById(idVehicule)
                .orElseThrow(() -> new RuntimeException("Véhicule introuvable avec l'ID: " + idVehicule));
        Equipement equipement = equipementRepository.findById(idEquipement)
                .orElseThrow(() -> new RuntimeException("Équipement introuvable avec l'ID: " + idEquipement));

        if (vehicule.getEquipements() == null) {
            vehicule.setEquipements(new ArrayList<>());
        }

        boolean alreadyPresent = vehicule.getEquipements().stream()
                .anyMatch(e -> e.getIdEquipement() != null && e.getIdEquipement().equals(idEquipement));

        if (!alreadyPresent) {
            vehicule.getEquipements().add(equipement);
        }

        return vehiculeRepository.save(vehicule);
    }

    @Override
    @Transactional
    public Vehicule desaffecterEquipement(Long idVehicule, Long idEquipement) {
        Vehicule vehicule = vehiculeRepository.findById(idVehicule)
                .orElseThrow(() -> new RuntimeException("Véhicule introuvable avec l'ID: " + idVehicule));
        Equipement equipement = equipementRepository.findById(idEquipement)
                .orElseThrow(() -> new RuntimeException("Équipement introuvable avec l'ID: " + idEquipement));

        if (vehicule.getEquipements() != null) {
            vehicule.getEquipements().removeIf(e -> e.getIdEquipement() != null && e.getIdEquipement().equals(idEquipement));
        }

        return vehiculeRepository.save(vehicule);
    }

    // Question 1 – Actualisation des données des véhicules
    // Démarre 5 secondes après le lancement, puis se répète toutes les 60 secondes
    @Override
    @Scheduled(initialDelay = 5000, fixedRate = 60000)
    public void actualiserEtatVehicules() {
        List<Vehicule> vehicules = vehiculeRepository.findAll();
        String dateHeure = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        log.info("[Actualisation Véhicules] Date et Heure: {} | Nombre total de véhicules enregistrés: {}",
                dateHeure, vehicules.size());
    }

    // Question 3 – Génération d'un rapport périodique
    // S'exécute automatiquement tous les lundis à 09h00
    @Override
    @Scheduled(cron = "0 0 9 ? * MON")
    public void genererRapportVehicules() {
        List<Vehicule> vehicules = vehiculeRepository.findAll();
        int total = vehicules.size();
        String dateHeure = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        log.info("================ RAPPORT PÉRIODIQUE DES VÉHICULES ================");
        log.info("Date et Heure de génération : {}", dateHeure);
        log.info("Nombre total de véhicules enregistrés : {}", total);
        log.info("==================================================================");
    }

    // Méthode de test simulant un traitement lourd pour tester PerformanceAspect (> 1000 ms)
    @Override
    public void testerMethodeLente() {
        try {
            Thread.sleep(1200); // 1.2 seconde
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

