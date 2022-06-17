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

import unc.ueJava.TPFinal.Model.Cours;
import unc.ueJava.TPFinal.Services.CoursService;
import unc.ueJava.TPFinal.Services.NiveauService;
import unc.ueJava.TPFinal.Services.SalleService;

@Controller
public class CoursController {

    @Autowired
    private CoursService coursService;

    @Autowired
    private NiveauService niveauService;

    @Autowired
    private SalleService salleService;

    @GetMapping("/cours")
    public String cours(Model model) {
        Iterable<Cours> liste_cours = coursService.getListeCours();
        model.addAttribute("liste_cours", liste_cours);
        return "cours_list";
    }

    /**
     * Page d'ajout
     * 
     * @return
     */
    @GetMapping("/cours/coursForm")
    public String ajouterCours(Model model) {
        Cours cours = new Cours();
        model.addAttribute("cours", cours);
        model.addAttribute("liste_niveaux", niveauService.getAllNiveaux());
        model.addAttribute("liste_salles", salleService.getAllSalles());
        return "cours_form";
    }

    /*
     * Page liste des élèves après avoir ajouter un nouveau cours
     */
    @PostMapping("/cours")
    public String cours(@ModelAttribute("cours") Cours cours, Model model) {
        if (!coursService.isHoraireAndSalleOk(cours)) {
            model.addAttribute("erreur", "La salle " + cours.getSalle() + " est utilisée aux horaires entrées");
        } else {
            coursService.saveCours(cours);
        }
        model.addAttribute("liste_cours", this.coursService.getListeCours());
        return "cours_list";
    }

    @GetMapping("/cours/{id}/edit")
    public String modifierCours(@PathVariable("id") int id, Model model) {
        Optional<Cours> cours = coursService.getCoursById(id);
        if (cours.isPresent()) {
            model.addAttribute("liste_niveaux", niveauService.getAllNiveaux());
            model.addAttribute("liste_salles", salleService.getAllSalles());
            model.addAttribute("cours", cours.get());
            return "cours_update";
        }
        return "redirect:/cours";
    }

    @PostMapping("/cours/{id}/update")
    public String modifierSalle(@PathVariable("id") int id, @Validated Cours cours, BindingResult result,
            Model model) {
        cours.setCoursId(id);
        coursService.saveCours(cours);
        model.addAttribute("liste_cours", this.coursService.getListeCours());
        return "cours_list";
    }

    @GetMapping("/cours/{id}/delete")
    public String deleteStudent(@PathVariable("id") int id, Model model) {
        Optional<Cours> cours = this.coursService.getCoursById(id);
        this.coursService.deleteCours(cours.get());
        model.addAttribute("liste_cours", this.coursService.getListeCours());
        return "cours_list";
    }
}
