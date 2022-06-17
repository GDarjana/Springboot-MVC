package unc.ueJava.TPFinal.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import unc.ueJava.TPFinal.DAO.SalleRepository;
import unc.ueJava.TPFinal.Model.Salle;

@Service
public class SalleService {

    @Autowired
    private SalleRepository salleRepository;

    public Iterable<Salle> getAllSalles() {
        return this.salleRepository.findAll();
    }

    public Optional<Salle> getSalleBySalleCode(String salleCode) {
        return this.salleRepository.findBySalleCode(salleCode);
    }

    public Optional<Salle> getSalleById(int salleId) {
        return this.salleRepository.findById(salleId);
    }

    public void saveSalle(Salle salle) {
        this.salleRepository.save(salle);
    }

    public void deleteSalle(Salle salle) {
        this.salleRepository.delete(salle);
    }

}
