package com.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//le @Data permet de faire la génération des get/set automatiquement
@Entity
@Data
public class Intervenant {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String mdp;
    private String nom;
    private String prenom;
    private String mail;
}
