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
    private List<Intervenant> Intervenants;
    @ManyToMany
    @JoinTable ( name = "join_cours_filiere_langues",
                 joinColumns = @JoinColumn( name = "cou_id" ),
                 inverseJoinColumns = @JoinColumn( name = "fil_id" ))
    private List<Filiere_langue> filiere_langues;
    @OneToMany (targetEntity = Creneau.class, mappedBy = "cours" )
    private List<Creneau> creneaux;
}
