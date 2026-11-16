package tn.autoloc.autoloc.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.autoloc.autoloc.entity.Agence;
import tn.autoloc.autoloc.entity.Vehicule;
import tn.autoloc.autoloc.repository.AgenceRepository;
import tn.autoloc.autoloc.repository.VehiculeRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class AgenceServiceImpl implements IAgenceService {

    private final AgenceRepository agenceRepository;
    private final VehiculeRepository vehiculeRepository;

    @Override
    public List<Agence> afficherToutesAgences() {
        return agenceRepository.findAll();
    }

    @Override
    public Agence ajouterAgence(Agence agence) {
        return agenceRepository.save(agence);
    }

    @Override
    public Agence modifierAgence(Agence agence) {
        return agenceRepository.save(agence);
    }

    @Override
    public Agence afficherAgenceById(Long id) {
        return agenceRepository.findById(id).orElse(null);
    }

    @Override
    public void supprimerAgence(Long idA) {
        agenceRepository.deleteById(idA);
    }

    @Override
    @Transactional
    public Agence ajouterAgenceEtAffecterVehicules(Agence agence) {
        List<Vehicule> vehicules = agence.getVehicules();

        // 1. Enregistrer l'agence dans la base de données
        Agence nouvelleAgence = agenceRepository.save(agence);

        // 2. Enregistrer chaque véhicule et l'affecter à la nouvelle agence
        if (vehicules != null && !vehicules.isEmpty()) {
            for (Vehicule vehicule : vehicules) {
                vehicule.setAgence(nouvelleAgence);
                vehiculeRepository.save(vehicule);
            }
        }

        nouvelleAgence.setVehicules(vehicules);
        return nouvelleAgence;
    }

    // Question 2 – Suivi des agences
    // S'exécute automatiquement avec un délai de 2 minutes (120000 ms) après la fin de chaque exécution
    @Override
    @Scheduled(fixedDelay = 120000)
    public void suivreNombreAgences() {
        List<Agence> agences = agenceRepository.findAll();
        String dateHeure = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        log.info("[Suivi Agences] Date et Heure: {} | Nombre total d'agences enregistrées: {}",
                dateHeure, agences.size());
    }
}
