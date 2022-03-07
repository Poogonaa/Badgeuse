package com.services;


import com.dtos.UtilisateurDto;

import java.util.List;

public interface IntervenantService {

    UtilisateurDto addIntervenant(UtilisateurDto utilisateurDto);

    UtilisateurDto getIntervenantById(Long intervenantId);

    boolean deleteIntervenant(Long intervenantId);

    List<UtilisateurDto> getAllIntervenants();


}
