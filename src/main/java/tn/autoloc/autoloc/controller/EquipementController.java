package tn.autoloc.autoloc.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.autoloc.autoloc.entity.Equipement;
import tn.autoloc.autoloc.service.IEquipementService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/equipements")
public class EquipementController {

    private final IEquipementService equipementService;

    @GetMapping({"", "/afficherTousEquipements"})
    public List<Equipement> afficherTousEquipements() {
        return equipementService.afficherTousEquipements();
    }

    @PostMapping("/ajouterEquipement")
    public Equipement ajouterEquipement(@RequestBody Equipement equipement) {
        return equipementService.ajouterEquipement(equipement);
    }

    @PutMapping("/modifierEquipement")
    public Equipement modifierEquipement(@RequestBody Equipement equipement) {
        return equipementService.modifierEquipement(equipement);
    }

    @GetMapping("/afficherEquipement/{id}")
    public Equipement afficherEquipement(@PathVariable("id") Long id) {
        return equipementService.afficherEquipementById(id);
    }

    @DeleteMapping("/supprimerEquipement/{id}")
    public void supprimerEquipement(@PathVariable("id") Long id) {
        equipementService.supprimerEquipement(id);
    }
}

