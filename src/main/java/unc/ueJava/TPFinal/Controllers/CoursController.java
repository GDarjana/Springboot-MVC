package unc.ueJava.TPFinal.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import unc.ueJava.TPFinal.DAO.CoursRepository;
import unc.ueJava.TPFinal.Model.Cours;

@Controller
public class CoursController {

    @Autowired
    private CoursRepository coursService;

    @GetMapping("/cours")
    public String cours(Model model) {
        Iterable<Cours> liste_cours = coursService.findAll();
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
        return "cours_form";
    }

    /*
     * Page liste des élèves après avoir ajouter un nouvel élève
     */
    @PostMapping("/cours")
    public String cours(@ModelAttribute("cours") Cours cours) {
        coursService.save(cours);
        return "redirect:/cours";
    }
}
