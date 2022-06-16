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

import unc.ueJava.TPFinal.DAO.SalleRepository;
import unc.ueJava.TPFinal.Model.Salle;

@Controller
public class SallesController {

    @Autowired
    private SalleRepository salleService;

    @GetMapping("/salles")
    public String salles(Model model) {
        Iterable<Salle> liste_salle = salleService.findAll();
        model.addAttribute("liste_salles", liste_salle);
        return "salles_list";
    }

    /**
     * Page d'ajout
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
     * Page liste des salles après avoir ajouter une nouvelle salle
     */
    @PostMapping("/salles")
    public String salles(@ModelAttribute("salle") Salle salle, Model model) {
        Optional<Salle> existingSalle = salleService.findBySalleCode(salle.getSalleCode());
        if (existingSalle.isPresent()) {
            model.addAttribute("erreur", "La salle " + existingSalle.get().getSalleCode() + " existe déjà ");
        } else {
            salleService.save(salle);
        }
        model.addAttribute("liste_salles", this.salleService.findAll());
        return "salles_list";
    }

    /*
     * Page de modification d'une salle existante
     */
    @GetMapping("/salles/{id}/edit")
    public String modifierSalle(@PathVariable("id") int id, Model model) {
        Optional<Salle> salle = salleService.findById(id);
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
        salleService.save(salle);
        model.addAttribute("liste_salles", this.salleService.findAll());
        return "salles_list";
    }

    @GetMapping("/salles/{id}/delete")
    public String deleteStudent(@PathVariable("id") int id, Model model) {
        Optional<Salle> salle = this.salleService.findById(id);
        this.salleService.delete(salle.get());
        model.addAttribute("liste_salles", this.salleService.findAll());
        model.addAttribute("erreur", "Yoya zi tintin");
        return "salles_list";
    }

}
