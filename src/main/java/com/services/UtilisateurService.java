package com.services;


import com.dtos.UtilisateurDto;

import java.util.List;

public interface UtilisateurService {

    UtilisateurDto addUtilisateur(UtilisateurDto utilisateurDto);

    UtilisateurDto getUtilisateurById(Long utilisateurId);

    boolean deleteUtilisateur(Long utilisateurId);

    List<UtilisateurDto> getAllUtilisateurs();


}
