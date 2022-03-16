package com.services;

import com.dtos.GestionnaireDto;
import com.dtos.UtilisateurDto;

import java.util.List;

public interface GestionnaireService{
        GestionnaireDto newGestionnaire(GestionnaireDto gestionnaireDto);

        GestionnaireDto getGestionnaireById(Long id);

        boolean deleteGestionnaire(Long id);

        List<GestionnaireDto> getAllGestionnaires();

        GestionnaireDto editGestionnaire(GestionnaireDto gestionnaireDto);
}
