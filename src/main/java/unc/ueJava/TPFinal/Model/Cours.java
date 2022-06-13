package unc.ueJava.TPFinal.Model;

import javax.persistence.Entity;

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

    @Column(name = "debut")
    private Date debut;

    @Column(name = "fin")
    private Date fin;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "cours_eleves", joinColumns = @JoinColumn(name = "nom_ue"), inverseJoinColumns = @JoinColumn(name = "numero_etudiant"))
    private List<Eleve> eleves = new ArrayList<>();

    public Cours() {
    }

    public Cours(String nom_ue, Date debut, Date fin) {
        this.nom_ue = nom_ue;
        this.debut = debut;
        this.fin = fin;
    }

    public void setNom_ue(String nom_ue) {
        this.nom_ue = nom_ue;
    }

    public String getNom_ue() {
        return this.nom_ue;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public Date getDebut() {
        return this.debut;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public Date getFin() {
        return this.fin;
    }

    public void addEleve(Eleve eleve) {
        this.eleves.add(eleve);
    }

    public List<Eleve> getEleves() {
        return this.eleves;
    }
}
