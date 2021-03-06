package com.services;

import com.dtos.UtilisateurDto;

import java.util.List;

public interface UtilisateurService {
    UtilisateurDto getUtilisateurById(Long id);

    List<UtilisateurDto> getAllUtilisateurs();

    UtilisateurDto connection(UtilisateurDto utilisateurDto);
}
