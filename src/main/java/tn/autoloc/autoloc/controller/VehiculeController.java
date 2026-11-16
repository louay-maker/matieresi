package tn.autoloc.autoloc.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.autoloc.autoloc.entity.Vehicule;
import tn.autoloc.autoloc.service.IVehiculeService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/vehicules")
public class VehiculeController {

    private final IVehiculeService vehiculeService;

    @GetMapping({"", "/afficherTousVehicules"})
    public List<Vehicule> afficherTousVehicules() {
        return vehiculeService.afficherTousVehicules();
    }

    @PostMapping("/ajouterVehicule")
    public Vehicule ajouterVehicule(@RequestBody Vehicule vehicule) {
        return vehiculeService.ajouterVehicule(vehicule);
    }

    @PutMapping("/modifierVehicule")
    public Vehicule modifierVehicule(@RequestBody Vehicule vehicule) {
        return vehiculeService.modifierVehicule(vehicule);
    }

    @GetMapping("/afficherVehicule/{id}")
    public Vehicule afficherVehicule(@PathVariable("id") Long id) {
        return vehiculeService.afficherVehiculeById(id);
    }

    @DeleteMapping("/supprimerVehicule/{id}")
    public void supprimerVehicule(@PathVariable("id") Long id) {
        vehiculeService.supprimerVehicule(id);
    }

    @PutMapping("/affecterEquipement/{idVehicule}/{idEquipement}")
    public Vehicule affecterEquipement(@PathVariable("idVehicule") Long idVehicule,
                                      @PathVariable("idEquipement") Long idEquipement) {
        return vehiculeService.affecterEquipement(idVehicule, idEquipement);
    }

    @PutMapping("/desaffecterEquipement/{idVehicule}/{idEquipement}")
    public Vehicule desaffecterEquipement(@PathVariable("idVehicule") Long idVehicule,
                                         @PathVariable("idEquipement") Long idEquipement) {
        return vehiculeService.desaffecterEquipement(idVehicule, idEquipement);
    }
}
