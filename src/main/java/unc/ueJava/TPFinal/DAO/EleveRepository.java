package unc.ueJava.TPFinal.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import unc.ueJava.TPFinal.Model.Eleve;

@Repository
public interface EleveRepository extends CrudRepository<Eleve, Integer> {

}
