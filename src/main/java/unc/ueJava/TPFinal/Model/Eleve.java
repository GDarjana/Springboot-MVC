package unc.ueJava.TPFinal.Model;

import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.DynamicUpdate;

import unc.ueJava.TPFinal.Model.Niveau.Niveau;

//import unc.ueJava.TPFinal.Model.Niveau.Niveau;

@Entity
@Table(name = "Eleve")
@DynamicUpdate
public class Eleve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int numero_etudiant;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "age")
    private int age;

    @Column(name = "adresse")
    private String adresse;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "cours_eleve", joinColumns = @JoinColumn(name = "numero_etudiant"), inverseJoinColumns = @JoinColumn(name = "id"))
    private List<Cours> listeCours;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "niveau_id", referencedColumnName = "id")
    private Niveau niveau;

    public Eleve() {
    }

    public Eleve(String nom, String prenom, int age, String adresse, Niveau niveau) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.adresse = adresse;
        this.niveau = niveau;
    }

    public int getNumero_etudiant() {
        return numero_etudiant;
    }

    public void setId(int numero_etudiant) {
        this.numero_etudiant = numero_etudiant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public List<Cours> getCours() {
        return listeCours;
    }

    public void addCours(Cours cours) {
        this.listeCours.add(cours);
    }

    public void setCours(List<Cours> cours) {
        this.listeCours = cours;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getAdresse() {
        return this.adresse;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return this.age;
    }

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }

    public Niveau getNiveau() {
        return this.niveau;
    }
}
