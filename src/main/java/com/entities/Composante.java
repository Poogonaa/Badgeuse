package com.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Composante {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomComposante;
    @OneToMany
    private List<Filiere_langue> filieres_langues;
}
