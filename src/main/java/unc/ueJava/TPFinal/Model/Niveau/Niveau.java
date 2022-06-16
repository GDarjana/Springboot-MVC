package unc.ueJava.TPFinal.Model.Niveau;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import unc.ueJava.TPFinal.Model.Cours;
import unc.ueJava.TPFinal.Model.Eleve;

@Entity
@Table(name = "Niveau")
@DynamicUpdate
public class Niveau {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "code")
    private CodeEnum code;

    @Enumerated(EnumType.STRING)
    @Column(name = "libelle")
    private LibelleEnum libelle;

    @OneToMany(mappedBy = "niveau")
    private List<Eleve> eleve;

    @OneToMany(mappedBy = "niveau")
    private List<Cours> cours;

    public Niveau() {
    }

    public Niveau(CodeEnum code, LibelleEnum libelle) {
        this.code = code;
        this.libelle = libelle;
    }

    public int getId() {
        return this.id;
    }

    public void setCode(CodeEnum code) {
        this.code = code;
    }

    public void setLibelle(LibelleEnum libelle) {
        this.libelle = libelle;
    }

    public CodeEnum getCode() {
        return this.code;
    }

    public LibelleEnum getLibelle() {
        return this.libelle;
    }

    public String toString() {
        return this.code + " " + this.libelle;
    }

}
