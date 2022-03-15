package com.services;

import com.dtos.GestionnaireDto;
import com.dtos.UtilisateurDto;

public interface GestionnaireService extends UtilisateurService{
        UtilisateurDto newGestionnaire(GestionnaireDto utilisateurDto);
}
