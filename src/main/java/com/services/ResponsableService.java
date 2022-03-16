package com.services;

import com.dtos.ComposanteDto;
import com.dtos.ResponsableDto;
import com.dtos.UtilisateurDto;

public interface ResponsableService extends UtilisateurService{
    UtilisateurDto newResponsable(ResponsableDto utilisateurDto);

    UtilisateurDto addComposante(ResponsableDto utilisateurDto);

    UtilisateurDto removeComposante(ResponsableDto utilisateurDto);
}
