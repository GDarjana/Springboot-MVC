package unc.ueJava.TPFinal.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import unc.ueJava.TPFinal.Model.Cours;

@Repository
public interface CoursRepository extends CrudRepository<Cours, Integer> {

}
