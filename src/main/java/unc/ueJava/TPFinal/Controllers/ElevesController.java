package unc.ueJava.TPFinal.Controllers;

import java.util.List;
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

import unc.ueJava.TPFinal.DAO.CoursRepository;
import unc.ueJava.TPFinal.DAO.NiveauRepository;
import unc.ueJava.TPFinal.Model.Cours;
import unc.ueJava.TPFinal.Model.Eleve;
import unc.ueJava.TPFinal.Services.EleveService;

@Controller
public class ElevesController {

    @Autowired
    private EleveService eleveService;

    @Autowired
    private NiveauRepository niveauService;

    @Autowired
    private CoursRepository coursService;

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

    /**
     * Page des élèves après avoir supprimer un élève
     * 
     * @param numeroEtudiant
     * @param model
     * @return
     */
    @GetMapping("/eleves/{numeroEtudiant}/delete")
    public String supprimerEtudiant(@PathVariable("numeroEtudiant") int numeroEtudiant, Model model) {
        this.eleveService.delEleve(numeroEtudiant);
        model.addAttribute("liste_eleves", this.eleveService.getAllEleves());
        return "eleves_list";
    }

    /*
     * Page des cours de l'élève
     */
    @GetMapping("/eleves/{numeroEtudiant}/cours/edit")
    public String coursEtudiant(@PathVariable("numeroEtudiant") int numeroEtudiant, Model model) {
        // Récupère l'éleve
        Optional<Eleve> eleve = eleveService.getEleve(numeroEtudiant);
        List<Cours> cours_disponibles = coursService.findAllByNiveauId(eleve.get().getNiveau().getId());
        cours_disponibles.removeAll(eleve.get().getCours());
        model.addAttribute("eleve", eleve.get());
        model.addAttribute("cours_disponibles", cours_disponibles);
        model.addAttribute("cours_inscrits", eleve.get().getCours());
        return "eleve_cours";
    }

    /*
     * Ajout d'un cours d'un élève
     */
    @GetMapping("/eleves/{numeroEtudiant}/cours/{id}/add")
    public String ajouterCours(@PathVariable("numeroEtudiant") int numeroEtudiant, @PathVariable("id") int cours_id,
            Model model) {
        Optional<Eleve> eleve = eleveService.getEleve(numeroEtudiant);
        Optional<Cours> coursToAdd = coursService.findById(cours_id);
        // A faire les vérifications
        if (coursToAdd.get().getEleves().size() + 1 <= coursToAdd.get().getSalle().getCapacite()) {
            coursToAdd.get().addEleve(eleve.get());
            eleve.get().addCours(coursToAdd.get());
            eleveService.saveEleve(eleve.get());
            coursService.save(coursToAdd.get());
            System.out.println("Liste des élèves dans le cours : " + coursToAdd.get().getEleves());
        } else {
            System.out.println("Liste des élèves dans le cours : " + coursToAdd.get().getEleves());
            model.addAttribute("erreur", "Le cours " + coursToAdd.get().toString() + " a atteint sa capacité maximale");
        }
        List<Cours> cours_disponibles = coursService.findAllByNiveauId(eleve.get().getNiveau().getId());
        cours_disponibles.removeAll(eleve.get().getCours());
        model.addAttribute("eleve", eleve.get());
        model.addAttribute("cours_disponibles", cours_disponibles);
        model.addAttribute("cours_inscrits", eleve.get().getCours());

        return "eleve_cours";

    }

    @GetMapping("/eleves/{numeroEtudiant}/cours/{id}/delete")
    public String supprimerCours(@PathVariable("numeroEtudiant") int numeroEtudiant, @PathVariable("id") int cours_id,
            Model model) {
        Optional<Eleve> eleve = eleveService.getEleve(numeroEtudiant);
        Optional<Cours> coursToDelete = coursService.findById(cours_id);
        eleve.get().removeCours(coursToDelete.get());
        eleveService.saveEleve(eleve.get());
        return "redirect:/eleves/" + numeroEtudiant + "/cours/edit";
    }

}
