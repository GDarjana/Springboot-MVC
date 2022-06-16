package unc.ueJava.TPFinal.Controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import unc.ueJava.TPFinal.DAO.NiveauRepository;
import unc.ueJava.TPFinal.Model.Eleve;
import unc.ueJava.TPFinal.Services.EleveService;

@Controller
public class ElevesController {

    @Autowired
    private EleveService eleveService;

    @Autowired
    private NiveauRepository niveauService;

    @GetMapping("/eleves")
    public String eleves(Model model) {
        Iterable<Eleve> liste_eleves = eleveService.getAllEleves();
        model.addAttribute("liste_eleves", liste_eleves);
        return "eleves_list";
    }

    /**
     * Page d'ajout
     * 
     * @return
     */
    @GetMapping("/eleves/eleveForm")
    public String ajouterEleve(Model model) {
        Eleve eleve = new Eleve();
        model.addAttribute("eleve", eleve);
        model.addAttribute("liste_niveaux", niveauService.findAll());
        return "eleve_form";
    }

    /*
     * Page liste des élèves après avoir ajouter un nouvel élève
     */
    @PostMapping("/eleves")
    public String eleves(@ModelAttribute("eleve") Eleve eleve) {
        eleveService.saveEleve(eleve);
        return "redirect:/eleves";
    }

    /**
     * Page de modification d'un élève déjà existant
     */
    @GetMapping("/eleves/{numeroEtudiant}/edit")
    public String modifierEleve(@PathVariable("numeroEtudiant") int numeroEtudiant, Model model) {
        Optional<Eleve> eleve = eleveService.getEleve(numeroEtudiant);
        if (eleve.isPresent()) {
            model.addAttribute("eleve", eleve.get());
            model.addAttribute("liste_cours", eleve.get().getCours());
            model.addAttribute("liste_niveaux", niveauService.findAll());
            return "eleve_update";
        }
        return "redirect:/eleves";
    }

    /*
     * Page de la liste des élèves après avoir modifié un élève existant
     */
    @PostMapping("/eleves/{numeroEtudiant}/update")
    public String modifierEleve(@PathVariable("numeroEtudiant") int numeroEtudiant, @Validated Eleve eleve,
            BindingResult result,
            Model model) {
        eleve.setNumeroEtudiant(numeroEtudiant);
        eleveService.saveEleve(eleve);
        model.addAttribute("liste_eleves", this.eleveService.getAllEleves());
        return "eleves_list";
    }

    @GetMapping("/eleves/{numeroEtudiant}/delete")
    public String supprimerEtudiant(@PathVariable("numeroEtudiant") int numeroEtudiant, Model model) {
        this.eleveService.delEleve(numeroEtudiant);
        model.addAttribute("liste_salles", this.eleveService.getAllEleves());
        return "eleves_list";
    }

}
