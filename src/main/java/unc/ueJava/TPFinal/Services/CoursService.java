package unc.ueJava.TPFinal.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import unc.ueJava.TPFinal.DAO.CoursRepository;

/**
 * Pour les cours
 * Définie ici les fonctionnalités principales d l'application , suppression ,
 * ajout etc ...
 */
@Service
public class CoursService {

    @Autowired
    private CoursRepository coursRepository;

}
