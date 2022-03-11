package com.services;

import com.dtos.SeanceFormationDto;

import java.util.List;

public interface SeanceFormationService {
    SeanceFormationDto getSeanceFormationById(Long id);

    boolean deleteSeanceFormation(Long id);

    List<SeanceFormationDto> getAllSeancesFormations();

    SeanceFormationDto editSeanceFormation(SeanceFormationDto seanceFromationDto);

    SeanceFormationDto addSeanceFormation(SeanceFormationDto seanceFormationDto);
}
