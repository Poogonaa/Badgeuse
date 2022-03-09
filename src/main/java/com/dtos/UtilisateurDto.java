package com.dtos;

import lombok.Data;

@Data
public class UtilisateurDto {

    private Long uti_id;
    private String login;
    private String mdp;
    private String nom;
    private String prenom;
    private String mail;
}
