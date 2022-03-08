package com.services;

import com.dtos.UtilisateurDto;

import java.util.List;

public interface IntervenantService extends UtilisateurService{
    UtilisateurDto addIntervenant(UtilisateurDto utilisateurDto);
}
