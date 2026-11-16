package tn.autoloc.autoloc.service;

import tn.autoloc.autoloc.entity.Agence;

import java.util.List;

public interface IAgenceService {
    List<Agence> afficherToutesAgences();
    Agence ajouterAgence(Agence agence);
    Agence modifierAgence(Agence agence);
    Agence afficherAgenceById(Long id);
    void supprimerAgence(Long idA);
    Agence ajouterAgenceEtAffecterVehicules(Agence agence);
    void suivreNombreAgences();
}
