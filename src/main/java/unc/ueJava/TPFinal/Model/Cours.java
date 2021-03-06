package unc.ueJava.TPFinal.Model;

import javax.persistence.Entity;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import unc.ueJava.TPFinal.Model.Niveau.Niveau;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "Cours", uniqueConstraints = { @UniqueConstraint(columnNames = { "date_debut", "date_fin", "salleId" }) })
@DynamicUpdate
public class Cours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int coursId;

    @Column(name = "nom_ue")
    private String nom_ue;

    @Column(name = "date_debut")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime dateDebut;

    @Column(name = "date_fin")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime dateFin;

    @ManyToOne()
    @JoinColumn(name = "salleId")
    private Salle salle;

    @ManyToMany(mappedBy = "listeCours")
    private List<Eleve> eleves = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "niveau_id", referencedColumnName = "id")
    private Niveau niveau;

    public Cours() {
    }

    public Cours(String nom_ue, LocalDateTime dateDebut, LocalDateTime dateFin, Niveau niveau, Salle salle) {
        this.nom_ue = nom_ue;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.niveau = niveau;
        this.salle = salle;
    }

    public int getCoursId() {
        return this.coursId;
    }

    public void setCoursId(int id) {
        this.coursId = id;
    }

    public void setNom_ue(String nom_ue) {
        this.nom_ue = nom_ue;
    }

    public String getNom_ue() {
        return this.nom_ue;
    }

    public void setDateDebut(LocalDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDateTime getDateDebut() {
        return this.dateDebut;
    }

    public String getDateDebutString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return this.dateDebut.format(formatter);
    }

    public void setDateFin(LocalDateTime dateFin) {
        this.dateFin = dateFin;
    }

    public LocalDateTime getDateFin() {
        return this.dateFin;
    }

    public String getDateFinString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return this.dateFin.format(formatter);
    }

    public void addEleve(Eleve eleve) {
        this.eleves.add(eleve);
    }

    public List<Eleve> getEleves() {
        return this.eleves;
    }

    public void setEleves(List<Eleve> eleves) {
        this.eleves = eleves;
        ;
    }

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }

    public Niveau getNiveau() {
        return this.niveau;
    }

    public Salle getSalle() {
        return this.salle;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }

    public String toString() {
        return this.nom_ue + " " + this.getDateDebutString() + " " + this.getDateFinString();
    }

    public boolean equals(Cours cours){
        if(this.coursId != cours.coursId){
            return false;
        }
        if(this.nom_ue != cours.nom_ue){
            return false;
        }
        if(this.dateDebut != cours.dateDebut){
            return false;
        }
        if(this.dateFin != cours.dateFin){
            return false;
        }
        if(this.niveau != cours.niveau){
            return false;
        }
        if(this.eleves != cours.eleves){
            return false;
        }
        return true;
    }

}
