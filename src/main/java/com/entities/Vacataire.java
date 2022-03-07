package com.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class Vacataire extends Utilisateur {

    @ManyToOne
    private Cours monCours;
    @OneToMany
    private List<SeanceFormation> mesSeances;

}
