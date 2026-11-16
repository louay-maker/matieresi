package tn.autoloc.autoloc.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.autoloc.autoloc.entity.Employe;
import tn.autoloc.autoloc.service.IEmployeService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/employes")
public class EmployeController {

    private final IEmployeService employeService;

    @GetMapping({"", "/afficherTousEmployes"})
    public List<Employe> afficherTousEmployes() {
        return employeService.afficherTousEmployes();
    }

    @PostMapping("/ajouterEmploye")
    public Employe ajouterEmploye(@RequestBody Employe employe) {
        return employeService.ajouterEmploye(employe);
    }

    @PutMapping("/modifierEmploye")
    public Employe modifierEmploye(@RequestBody Employe employe) {
        return employeService.modifierEmploye(employe);
    }

    @GetMapping("/afficherEmploye/{id}")
    public Employe afficherEmploye(@PathVariable("id") Long id) {
        return employeService.afficherEmployeById(id);
    }

    @DeleteMapping("/supprimerEmploye/{id}")
    public void supprimerEmploye(@PathVariable("id") Long id) {
        employeService.supprimerEmploye(id);
    }

    @PutMapping("/desaffecterEmploye/{idEmploye}")
    public Employe desaffecterEmploye(@PathVariable("idEmploye") Long idEmploye) {
        return employeService.desaffecterEmploye(idEmploye);
    }

    @PutMapping("/affecterEmploye/{idEmploye}/{idAgence}")
    public Employe affecterEmploye(@PathVariable("idEmploye") Long idEmploye,
                                  @PathVariable("idAgence") Long idAgence) {
        return employeService.affecterEmploye(idEmploye, idAgence);
    }
}

