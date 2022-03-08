package com.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Filiere_langue {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String nom;
    @ManyToOne
    private Composante composante;
    @ManyToMany
    private List<Cours> cours;
}
