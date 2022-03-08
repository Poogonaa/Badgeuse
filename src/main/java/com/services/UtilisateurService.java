package com.services;

import com.dtos.UtilisateurDto;

import java.util.List;

public interface UtilisateurService {
    UtilisateurDto getUtilisateurById(UtilisateurDto utilisateurDto);

    boolean deleteUtilisateur(UtilisateurDto utilisateurDto);

    List<UtilisateurDto> getAllUtilisateurs();

    UtilisateurDto editUtilisateur(UtilisateurDto utilisateurDto);
}
