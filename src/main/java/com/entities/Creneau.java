package com.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Creneau {
    @Id @GeneratedValue
    private int id;
    private int duree;
    private String date_heure;
    @ManyToOne
    private Cours monCours;

    @OneToMany
    private List<SeanceFormation> mesSeances;


}
