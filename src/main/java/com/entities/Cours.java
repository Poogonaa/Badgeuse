package com.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Cours {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cou_id;
    private String intitule;

    @ManyToMany
    @JoinTable ( name = "join_cours_intervenants",
                 joinColumns = @JoinColumn( name = "cou_id" ),
                 inverseJoinColumns = @JoinColumn( name = "uti_id" ))
    private List<Intervenant> intervenants;

    @ManyToMany
    @JoinTable ( name = "join_cours_filiere_langues",
                 joinColumns = @JoinColumn( name = "cou_id" ),
                 inverseJoinColumns = @JoinColumn( name = "fil_id" ))
    private List<Filiere_langue> filiere_langues;

    @OneToMany (targetEntity = Creneau.class, mappedBy = "cours" )
    private List<Creneau> creneaux;

    public void addIntervenant(Intervenant intervenant){
        this.intervenants.add(intervenant);
    }

    public void addFiliere_langue(Filiere_langue filiere_langue){
        this.filiere_langues.add(filiere_langue);
    }

    public void addCreneau(Creneau creneau){
        this.creneaux.add(creneau);
    }

    public void removeIntervenant(Intervenant intervenant){
        this.intervenants.remove(intervenant);
    }

    public void removeFiliere_langue(Filiere_langue filiere_langue){
        this.filiere_langues.remove(filiere_langue);
    }

    public void removeCreneau(Creneau creneau){
        this.creneaux.remove(creneau);
    }
}
