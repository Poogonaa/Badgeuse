package com.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Creneau {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cre_id;
    private String heure_debut;
    private String date;
    private Integer duree;
    private String type;
    private  String salle;
    @ManyToOne @JoinColumn( name = "cou_id" )
    private Cours cours;
    @OneToMany (targetEntity = SeanceFormation.class, mappedBy = "creneau" )
    private List<SeanceFormation> seanceFormations;
}
