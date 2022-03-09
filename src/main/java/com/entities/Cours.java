package com.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Cours {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String intitule;
    @OneToMany
    private List<Creneau> Creneaux;
    @ManyToMany
    private List<Intervenant> Intervenants;
}
