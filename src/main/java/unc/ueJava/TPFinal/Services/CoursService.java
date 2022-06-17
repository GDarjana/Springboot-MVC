package unc.ueJava.TPFinal.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import unc.ueJava.TPFinal.DAO.CoursRepository;
import unc.ueJava.TPFinal.Model.Cours;

/**
 * Pour les cours
 * Définie ici les fonctionnalités principales d l'application , suppression ,
 * ajout etc ...
 */
@Service
public class CoursService {

    @Autowired
    private CoursRepository coursRepository;

    public boolean isHoraireAndSalleOk(Cours cours) {
        if (coursRepository.findByDateDebutLessThanEqualAndDateFinGreaterThanEqualAndSalle(cours.getDateFin(),
                cours.getDateDebut(), cours.getSalle()).isPresent()) {
            return false;
        }
        return true;
    }

}
