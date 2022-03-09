package com.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Filiere_langue {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fil_id;
    private String code;
    private String nom;
    @ManyToOne @JoinColumn( name = "com_id" )
    private Composante composante;
    @ManyToMany
    @JoinTable ( name = "join_cours_filiere_langues",
                 joinColumns = @JoinColumn( name = "fil_id" ),
                 inverseJoinColumns = @JoinColumn( name = "cou_id" ))
    private List<Cours> cours;
}
