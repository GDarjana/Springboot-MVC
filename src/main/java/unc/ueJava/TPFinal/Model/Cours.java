package unc.ueJava.TPFinal.Model;

import javax.persistence.Entity;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "Cours")
public class Cours {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "nomUE")
    private String nomUE;

    @Column(name = "debut")
    private Date debut;

    @Column(name = "fin")
    private Date fin;

    public Cours() {
    }

    public Cours(String nomUE, Date debut, Date fin) {
        this.nomUE = nomUE;
        this.debut = debut;
        this.fin = fin;
    }

    public void setNomUE(String nomUE) {
        this.nomUE = nomUE;
    }

    public String getNomUE() {
        return this.nomUE;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public Date getDebut() {
        return this.debut;
    }

    public void setfin(Date fin) {
        this.fin = fin;
    }

    public Date getfin() {
        return this.fin;
    }
}
