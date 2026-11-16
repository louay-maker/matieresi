package tn.autoloc.autoloc.service;

import tn.autoloc.autoloc.entity.Contrat;

import java.util.List;

public interface IContratService {
    List<Contrat> afficherTousContrats();
    Contrat ajouterContrat(Contrat contrat);
    Contrat modifierContrat(Contrat contrat);
    Contrat afficherContratById(Long id);
    void supprimerContrat(Long id);
}
