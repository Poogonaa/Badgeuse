package com.services;

import com.dtos.UtilisateurDto;

public interface ResponsableService extends UtilisateurService{
    UtilisateurDto addResponsable(UtilisateurDto utilisateurDto);
}
