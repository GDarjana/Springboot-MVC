package unc.ueJava.TPFinal.Model.Niveau;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Niveau")
public class Niveau {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_niveau;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "code")
    private CodeEnum code;

    @Enumerated(EnumType.STRING)
    @Column(name = "libelle")
    private LibelleEnum libelle;

    public Niveau() {
    }

    public Niveau(CodeEnum code, LibelleEnum libelle) {
        this.code = code;
        this.libelle = libelle;
    }

    public void setCode(CodeEnum code){
        this.code = code;
    }

    public void setLibelle(LibelleEnum libelle){
        this.libelle = libelle;
    }

    public CodeEnum getCode(){
        return this.code;
    }

    public LibelleEnum getLibelle(){
        return this.libelle;
    }

}
