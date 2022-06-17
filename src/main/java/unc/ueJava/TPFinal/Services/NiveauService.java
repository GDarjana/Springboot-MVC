package unc.ueJava.TPFinal.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import unc.ueJava.TPFinal.DAO.NiveauRepository;
import unc.ueJava.TPFinal.Model.Niveau.Niveau;

@Service
public class NiveauService {
    @Autowired
    private NiveauRepository niveauRepository;

    public Iterable<Niveau> getAllNiveaux() {
        return this.niveauRepository.findAll();
    }

}
