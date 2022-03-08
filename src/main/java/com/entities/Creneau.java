package com.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Creneau {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String date_heure;
    private Integer duree;
    private String type;
    private  String salle;
    @ManyToOne
    private Cours cours;
}
