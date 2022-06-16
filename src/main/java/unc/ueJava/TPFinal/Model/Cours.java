package unc.ueJava.TPFinal.Model;

import javax.persistence.Entity;

import org.springframework.format.annotation.DateTimeFormat;

import unc.ueJava.TPFinal.Model.Niveau.Niveau;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "Cours")
public class Cours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nom_ue")
    private String nom_ue;

    @Column(name = "date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @Column(name = "horaire_debut")
    private String horaire_debut;

    @Column(name = "horaire_fin")
    private String horaire_fin;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "salleId")
    private Salle salle;

    @ManyToMany(mappedBy = "listeCours")
    private List<Eleve> eleves = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "niveau_id", referencedColumnName = "id")
    private Niveau niveau;

    public Cours() {
    }

    public Cours(String nom_ue, LocalDate date, String horaire_debut, String horaire_fin, Niveau niveau) {
        this.nom_ue = nom_ue;
        this.date = date;
        this.horaire_debut = horaire_debut;
        this.horaire_fin = horaire_fin;
        this.niveau = niveau;
    }

    public void setNom_ue(String nom_ue) {
        this.nom_ue = nom_ue;
    }

    public String getNom_ue() {
        return this.nom_ue;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
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
