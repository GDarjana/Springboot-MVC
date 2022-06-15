package unc.ueJava.TPFinal.DAO;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import unc.ueJava.TPFinal.Model.Niveau.Niveau;

public interface NiveauRepository extends CrudRepository<Niveau, Integer>{
}
