package tn.autoloc.autoloc.service;

import tn.autoloc.autoloc.entity.Equipement;

import java.util.List;

public interface IEquipementService {
    List<Equipement> afficherTousEquipements();
    Equipement ajouterEquipement(Equipement equipement);
    Equipement modifierEquipement(Equipement equipement);
    Equipement afficherEquipementById(Long id);
    void supprimerEquipement(Long id);
}

