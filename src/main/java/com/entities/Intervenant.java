package com.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Intervenant extends Utilisateur{
    @ManyToMany
    @JoinTable( name = "join_cours_intervenants",
                joinColumns = @JoinColumn( name = "uti_id" ),
                inverseJoinColumns = @JoinColumn( name = "cou_id" ))
    private List<Cours> cours;
    @OneToMany (targetEntity = SeanceFormation.class, mappedBy = "intervenant" )
    private List<SeanceFormation> seanceFormations;
}
