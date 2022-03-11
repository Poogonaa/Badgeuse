package com.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Utilisateur {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uti_id;
    @Column(unique = true)
    private String login;
    @Column(length = 256)
    private String mdp;
    private String nom;
    private String prenom;
    private String mail;
    @Column( insertable = false, updatable = false)
    private String dtype;
}
