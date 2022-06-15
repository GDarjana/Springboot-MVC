package unc.ueJava.TPFinal.Model;

import javax.persistence.*;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "salle")
@DynamicUpdate
public class Salle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "salleCode")
    private String salleCode;

    @Column(name = "nom")
    private String nom;

    @Column(name = "capacite")
    private int capacite;

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

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
