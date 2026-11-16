package tn.autoloc.autoloc.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.autoloc.autoloc.entity.Agence;
import tn.autoloc.autoloc.service.IAgenceService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/agences")
public class AgenceController {

    private final IAgenceService agenceService;

    @GetMapping({"", "/afficherToutesAgences"})
    public List<Agence> getAllAgence() {
        return agenceService.afficherToutesAgences();
    }

    @PostMapping("/ajouterAgence")
    public Agence ajouterAgence(@RequestBody Agence agence) {
        return agenceService.ajouterAgence(agence);
    }

    @PostMapping("/ajouterAgenceEtAffecterVehicules")
    public Agence ajouterAgenceEtAffecterVehicules(@RequestBody Agence agence) {
        return agenceService.ajouterAgenceEtAffecterVehicules(agence);
    }

    @PutMapping("/modifierAgence")
    public Agence modifierAgence(@RequestBody Agence agence) {
        return agenceService.modifierAgence(agence);
    }

    @GetMapping("/afficherAgence/{id}")
    public Agence afficherAgence(@PathVariable("id") Long id) {
        return agenceService.afficherAgenceById(id);
    }

    @DeleteMapping("/supprimerAgence/{idA}")
    public void supprimerAgence(@PathVariable("idA") Long idA) {
        agenceService.supprimerAgence(idA);
    }
}