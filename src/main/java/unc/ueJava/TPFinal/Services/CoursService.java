package unc.ueJava.TPFinal.Services;

import java.util.List;
import java.util.Optional;

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

    public Iterable<Cours> getListeCours() {
        return this.coursRepository.findAll();
    }

    public Optional<Cours> getCoursById(int coursId) {
        return this.coursRepository.findById(coursId);
    }

    public void saveCours(Cours cours) {
        this.coursRepository.save(cours);
    }

    public void deleteCours(Cours cours) {
        this.coursRepository.delete(cours);
    }

    public List<Cours> getAllCoursByNiveauId(int niveauId) {
        return this.coursRepository.findAllByNiveauId(niveauId);
    }

    public boolean isHoraireAndSalleOk(Cours cours) {
        if (coursRepository.findByDateDebutLessThanEqualAndDateFinGreaterThanEqualAndSalle(cours.getDateFin(),
                cours.getDateDebut(), cours.getSalle()).isPresent()) {
            return false;
        }
        return true;
    }

}
