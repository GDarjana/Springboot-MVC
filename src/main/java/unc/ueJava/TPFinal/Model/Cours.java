package unc.ueJava.TPFinal.Model;

import javax.persistence.Entity;

import org.springframework.format.annotation.DateTimeFormat;

import unc.ueJava.TPFinal.Model.Niveau.Niveau;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "Cours", uniqueConstraints = { @UniqueConstraint(columnNames = { "date_debut", "date_fin", "salleId" }) })
public class Cours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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

    public Cours(String nom_ue, LocalDateTime dateDebut, LocalDateTime dateFin, Niveau niveau) {
        this.nom_ue = nom_ue;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.niveau = niveau;
    }

    public int getCoursId(){
        return this.id;
    }

    public void setCoursId(int id){
        this.id = id;
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

    public String getDateDebutString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return this.dateDebut.format(formatter);
    }

    public void setDateFin(LocalDateTime dateFin) {
        this.dateFin = dateFin;
    }

    public LocalDateTime getDateFin() {
        return this.dateFin;
    }

    public String getDateFinString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return this.dateFin.format(formatter);
    }

    public void addEleve(Eleve eleve) {
        this.eleves.add(eleve);
    }

    public List<Eleve> getEleves() {
        return this.eleves;
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

}
