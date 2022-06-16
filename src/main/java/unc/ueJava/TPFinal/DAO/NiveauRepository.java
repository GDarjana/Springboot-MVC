package unc.ueJava.TPFinal.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import unc.ueJava.TPFinal.Model.Niveau.Niveau;

@Repository
public interface NiveauRepository extends CrudRepository<Niveau, Integer> {

}
