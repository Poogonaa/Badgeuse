package com.services;

import com.dtos.IntervenantDto;

import java.util.List;

public interface IntervenantService{
    IntervenantDto newIntervenant(IntervenantDto intervenantDto);

    IntervenantDto getIntervenantById(Long id);

    List<IntervenantDto> getAllIntervenants();

    IntervenantDto editIntervenant(IntervenantDto intervenantDto);

    IntervenantDto addCours(IntervenantDto intervenantDto);

    IntervenantDto addSeanceFormation(IntervenantDto intervenantDto);

    IntervenantDto removeCours(IntervenantDto intervenantDto);

    IntervenantDto removeSeanceFormation(IntervenantDto intervenantDto);

    boolean deleteIntervenant(Long id);
}
