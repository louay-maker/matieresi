package tn.autoloc.autoloc.service;

import tn.autoloc.autoloc.entity.Vehicule;

import java.util.List;

public interface IVehiculeService {
    List<Vehicule> afficherTousVehicules();
    Vehicule ajouterVehicule(Vehicule vehicule);
    Vehicule modifierVehicule(Vehicule vehicule);
    Vehicule afficherVehiculeById(Long id);
    void supprimerVehicule(Long id);
    Vehicule affecterEquipement(Long idVehicule, Long idEquipement);
    Vehicule desaffecterEquipement(Long idVehicule, Long idEquipement);
    void actualiserEtatVehicules();
    void genererRapportVehicules();
}

