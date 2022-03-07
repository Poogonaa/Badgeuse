package com.services;


import com.dtos.IntervenantDto;

import java.util.List;

public interface IntervenantService {

    IntervenantDto addIntervenant(IntervenantDto intervenantDto);

    IntervenantDto getIntervenantById(Long intervenantId);

    boolean deleteIntervenant(Long intervenantId);

    List<IntervenantDto> getAllIntervenants();


}
