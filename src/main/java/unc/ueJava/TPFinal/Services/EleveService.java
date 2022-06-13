package unc.ueJava.TPFinal.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import unc.ueJava.TPFinal.DAO.EleveRepository;
import unc.ueJava.TPFinal.Model.Eleve;

/**
 * Pour les eleves
 * Définie ici les fonctionnalités principales d l'application , suppression ,
 * ajout etc ...
 */
@Service
@Transactional
public class EleveService {

    @Autowired
    private EleveRepository eleveRepository;

    /**
     * ● La consultation des élèves
     * ● La création d’un nouvel élève
     * ● La modification d’un élève existant
     * ● La suppression d’un élève
     */

    /**
     * La consultation des élèves
     */
    public Iterable<Eleve> getAllEleves() {
        return this.eleveRepository.findAll();
    }

    /**
     * La création d'un élève
     */
    public Eleve saveEleve(Eleve eleve) {
        return this.eleveRepository.save(eleve);
    }

    /**
     * La suppression d'un élève
     */
    public void delEleve(int numeroEtudiant) {
        this.eleveRepository.deleteById(numeroEtudiant);
    }

    /**
     * Consulte un élève
     */
    public Optional<Eleve> getEleve(int numero_etudiant) {
        return this.eleveRepository.findById(numero_etudiant);
    }

}
