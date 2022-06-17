package unc.ueJava.TPFinal.DAO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import unc.ueJava.TPFinal.Model.Cours;
import unc.ueJava.TPFinal.Model.Salle;

@Repository
public interface CoursRepository extends CrudRepository<Cours, Integer> {

    Optional<Cours> findByDateDebutLessThanEqualAndDateFinGreaterThanEqualAndSalle(LocalDateTime dateFin,
            LocalDateTime dateDebut, Salle salle);

    List<Cours> findAllByNiveauId(int niveau_id);

}
