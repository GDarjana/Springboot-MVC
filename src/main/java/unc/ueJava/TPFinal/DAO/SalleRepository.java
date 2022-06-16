package unc.ueJava.TPFinal.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import unc.ueJava.TPFinal.Model.Salle;

@Repository
public interface SalleRepository extends CrudRepository<Salle, Integer> {

}
