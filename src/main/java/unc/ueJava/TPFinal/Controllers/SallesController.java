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

import unc.ueJava.TPFinal.Model.Salle;
import unc.ueJava.TPFinal.Services.SalleService;

@Controller
public class SallesController {

    @Autowired
    private SalleService salleService;

    /*
     * Page d'affichage de la liste des salles
     */
    @GetMapping("/salles")
    public String salles(Model model) {
        Iterable<Salle> liste_salle = salleService.getAllSalles();
        model.addAttribute("liste_salles", liste_salle);
        return "salles_list";
    }

    /**
     * Page d'ajout d'une salle
     * 
     * @return
     */
    @GetMapping("/salles/salleForm")
    public String ajouterSalle(Model model) {
        Salle salle = new Salle();
        model.addAttribute("salle", salle);
        return "salle_form";
    }

    /*
     * Page de la liste des salles après avoir ajouté une salle
     */
    @PostMapping("/salles")
    public String salles(@ModelAttribute("salle") Salle salle, Model model) {
        Optional<Salle> existingSalle = salleService.getSalleBySalleCode(salle.getSalleCode());
        if (existingSalle.isPresent()) {
            model.addAttribute("erreur", "La salle " + existingSalle.get().getSalleCode() + " existe déjà ");
        } else {
            salleService.saveSalle(salle);
        }
        model.addAttribute("liste_salles", this.salleService.getAllSalles());
        return "salles_list";
    }

    /*
     * Page de modification d'une salle existante
     */
    @GetMapping("/salles/{id}/edit")
    public String modifierSalle(@PathVariable("id") int id, Model model) {
        Optional<Salle> salle = salleService.getSalleById(id);
        if (salle.isPresent()) {
            model.addAttribute("salle", salle.get());
            return "salle_update";
        }
        return "redirect:/salles";
    }

    /*
     * Page de la liste des salles après avoir modifié une salle existante
     */
    @PostMapping("/salles/{id}/update")
    public String modifierSalle(@PathVariable("id") int id, @Validated Salle salle, BindingResult result,
            Model model) {
        salle.setSalleId(id);
        salleService.saveSalle(salle);
        model.addAttribute("liste_salles", this.salleService.getAllSalles());
        return "salles_list";
    }

    /*
     * Page de la liste des salles après avoir supprimé
     */
    @GetMapping("/salles/{id}/delete")
    public String supprimerSalle(@PathVariable("id") int id, Model model) {
        Optional<Salle> salle = this.salleService.getSalleById(id);
        if (salle.get().getListeCours().isEmpty()) {
            this.salleService.deleteSalle(salle.get());
        } else {
            model.addAttribute("erreur", "La salle " + salle.get().getSalleCode() + " est utilisé pour des cours");
        }
        model.addAttribute("liste_salles", this.salleService.getAllSalles());
        return "salles_list";
    }

}
