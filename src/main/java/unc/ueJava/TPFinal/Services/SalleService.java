package unc.ueJava.TPFinal.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import unc.ueJava.TPFinal.DAO.SalleRepository;

@Service
public class SalleService {

    @Autowired
    private SalleRepository salleRepository;

}
