package unc.ueJava.TPFinal.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import unc.ueJava.TPFinal.DAO.NiveauRepository;

@Service
public class NiveauService {
    @Autowired
    private NiveauRepository niveauRepository;
}
