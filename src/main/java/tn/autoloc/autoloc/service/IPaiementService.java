package tn.autoloc.autoloc.service;

import tn.autoloc.autoloc.entity.Paiement;

import java.util.List;

public interface IPaiementService {
    List<Paiement> afficherTousPaiements();
    Paiement ajouterPaiement(Paiement paiement);
    Paiement modifierPaiement(Paiement paiement);
    Paiement afficherPaiementById(Long id);
    void supprimerPaiement(Long id);
}
