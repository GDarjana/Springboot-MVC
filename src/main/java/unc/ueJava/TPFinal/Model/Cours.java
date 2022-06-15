package unc.ueJava.TPFinal.Model;

import javax.persistence.Entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "Cours")
public class Cours {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "nom_ue")
    private String nom_ue;

    @Column(name = "date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @Column(name = "horaire_debut")
    private String horaire_debut;

    @Column(name = "horaire_fin")
    private String horaire_fin;

    /**
     * @ManyToOne(cascade = CascadeType.ALL)
     * @JoinColumn(name = "salle_code")
     *                  private Salle salle;
     */

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "cours_eleves", joinColumns = @JoinColumn(name = "nom_ue"), inverseJoinColumns = @JoinColumn(name = "numero_etudiant"))
    private List<Eleve> eleves = new ArrayList<>();

    public Cours() {
    }

    public Cours(String nom_ue, Date date, String horaire_debut, String horaire_fin) {
        this.nom_ue = nom_ue;
        this.date = date;
        this.horaire_debut = horaire_debut;
        this.horaire_fin = horaire_fin;
    }

    public void setNom_ue(String nom_ue) {
        this.nom_ue = nom_ue;
    }

    public String getNom_ue() {
        return this.nom_ue;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return this.date;
    }

    public void setHoraire_debut(String horaire_debut) {
        this.horaire_debut = horaire_debut;
    }

    public String getHoraire_debut() {
        return this.horaire_debut;
    }

    public void setHoraire_fin(String horaire_fin) {
        this.horaire_fin = horaire_fin;
    }

    public String getHoraire_fin() {
        return this.horaire_fin;
    }

    public void addEleve(Eleve eleve) {
        this.eleves.add(eleve);
    }

    public List<Eleve> getEleves() {
        return this.eleves;
    }

}
