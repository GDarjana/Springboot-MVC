package unc.ueJava.TPFinal.Model.Niveau;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Niveau")
public class Niveau {

    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "code")
    private CodeEnum code;

    @Enumerated(EnumType.STRING)
    @Column(name = "libelle")
    private LibelleEnum libelle;
}
