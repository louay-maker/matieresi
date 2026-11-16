package tn.autoloc.autoloc.service;

import tn.autoloc.autoloc.entity.Employe;

import java.util.List;

public interface IEmployeService {
    List<Employe> afficherTousEmployes();
    Employe ajouterEmploye(Employe employe);
    Employe modifierEmploye(Employe employe);
    Employe afficherEmployeById(Long id);
    void supprimerEmploye(Long id);
    Employe desaffecterEmploye(Long idEmploye);
    Employe affecterEmploye(Long idEmploye, Long idAgence);
}
