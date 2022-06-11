package unc.ueJava.TPFinal.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import unc.ueJava.TPFinal.Model.Eleve;
import unc.ueJava.TPFinal.Services.EleveService;

@Controller
public class ElevesController {

    @Autowired
    private EleveService eleveService;

    @GetMapping("/eleves")
    public String eleves(Model model) {
        Iterable<Eleve> liste_eleves = eleveService.getAllEleves();
        model.addAttribute("liste_eleves", liste_eleves);
        return "eleves";
    }

    /**
     * Page d'ajout
     * 
     * @return
     */
    @GetMapping("/eleves/ajouter")
    public String ajouterEleve(Model model) {
        Eleve eleve = new Eleve();
        model.addAttribute("eleve", eleve);
        return "new_eleve";
    }

    /*
     * Page liste des élèves après avoir ajouter un nouvel élève
     */
    @PostMapping("/eleves")
    public String eleves(@ModelAttribute("eleve") Eleve eleve) {
        eleveService.saveEleve(eleve);
        return "redirect:/eleves";
    }

}
