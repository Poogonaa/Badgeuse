package com.services;

import com.dtos.CoursDto;
import com.dtos.IntervenantDto;
import com.dtos.SeanceFormationDto;
import com.dtos.UtilisateurDto;

public interface IntervenantService extends UtilisateurService{
    UtilisateurDto newIntervenant(IntervenantDto utilisateurDto);

    UtilisateurDto addCours(IntervenantDto utilisateurDto);

    UtilisateurDto addSeanceFormation(IntervenantDto utilisateurDto);

    UtilisateurDto removeCours(IntervenantDto utilisateurDto);

    UtilisateurDto removeSeanceFormation(IntervenantDto utilisateurDto);
}
