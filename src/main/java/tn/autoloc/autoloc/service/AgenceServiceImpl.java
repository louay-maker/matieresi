package tn.autoloc.autoloc.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.autoloc.autoloc.entity.Agence;
import tn.autoloc.autoloc.entity.Vehicule;
import tn.autoloc.autoloc.repository.AgenceRepository;
import tn.autoloc.autoloc.repository.VehiculeRepository;

import java.util.List;

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
}
