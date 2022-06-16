package unc.ueJava.TPFinal.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "salle")
@DynamicUpdate
public class Salle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "salleId")
    private int salleId;

    @Column(name = "salleCode")
    private String salleCode;

    @Column(name = "nom")
    private String nom;

    @Column(name = "capacite")
    private int capacite;

    @OneToMany(mappedBy = "salle", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cours> listeCours = new ArrayList<>();

    public Salle() {
    }

    public Salle(String salleCode, String nom, int capacite) {
        this.salleCode = salleCode;
        this.nom = nom;
        this.capacite = capacite;
    }

    public void setSalleCode(String salleCode) {
        this.salleCode = salleCode;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public String getSalleCode() {
        return this.salleCode;
    }

    public String getNom() {
        return this.nom;
    }

    public int getCapacite() {
        return this.capacite;
    }

    public int getSalleId() {
        return this.salleId;
    }

    public void setSalleId(int id) {
        this.salleId = id;
    }

    public String toString() {
        return this.salleCode + " " + this.nom + " " + this.capacite;
    }

    public List<Cours> getListeCours() {
        return this.listeCours;
    }

    public void setListeCours(List<Cours> listeCours) {
        this.listeCours = listeCours;
    }

    public void addComment(Cours cours) {
        listeCours.add(cours);
        cours.setSalle(this);
    }

    public void removeComment(Cours cours) {
        listeCours.remove(cours);
        cours.setSalle(null);
    }

}
