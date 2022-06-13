package unc.ueJava.TPFinal.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import unc.ueJava.TPFinal.DAO.CoursRepository;
import unc.ueJava.TPFinal.Model.Cours;

@Controller
public class CoursController {

    @Autowired
    private CoursRepository coursService;

    @GetMapping("/cours")
    public String eleves(Model model) {
        Iterable<Cours> liste_cours = coursService.findAll();
        model.addAttribute("liste_cours", liste_cours);
        return "cours_list";
    }
}
