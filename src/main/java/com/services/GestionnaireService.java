package com.services;

import com.dtos.UtilisateurDto;

import java.util.List;

public interface GestionnaireService extends UtilisateurService{
        UtilisateurDto addGestionnaire(UtilisateurDto utilisateurDto);
}
