package unc.ueJava.TPFinal.Model;

import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.DynamicUpdate;

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

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Cours> cours;

    // private Niveau niveau;

    public Eleve() {
    }

    public Eleve(String nom, String prenom, int age, String adresse) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.adresse = adresse;
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
        return cours;
    }

    public void addCours(Cours cours) {
        this.cours.add(cours);
    }

    public void setCours(List<Cours> cours) {
        this.cours = cours;
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
}
