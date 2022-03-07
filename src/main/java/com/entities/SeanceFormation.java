package com.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class SeanceFormation {

    @Id
    private int id;
    private boolean estEffectue;
    private int dureeEffective;
    private boolean valide;
    private String commentaire;

    @ManyToOne
    private Vacataire monVacataire;
    @ManyToOne
    private Creneau monCreneau;


}
