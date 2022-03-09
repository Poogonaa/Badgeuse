package com.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class SeanceFormation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sea_id;
    private Boolean estEffectue;
    private int dureeEffective;
    private Boolean valide;
    private String commentaire;
    @ManyToOne @JoinColumn( name = "uti_id" )
    private Intervenant intervenant;
    @ManyToOne @JoinColumn( name = "cre_id" )
    private Creneau creneau;
}
