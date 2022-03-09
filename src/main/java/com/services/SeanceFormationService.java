package com.services;

import com.dtos.SeanceFormationDto;

import java.util.List;

public interface SeanceFormationService {
    SeanceFormationDto getSeanceFormationById(SeanceFormationDto seanceFromationDto);

    boolean deleteSeanceFormation(SeanceFormationDto seanceFromationDto);

    List<SeanceFormationDto> getAllSeancesFormations();

    SeanceFormationDto editSeanceFormation(SeanceFormationDto seanceFromationDto);

    SeanceFormationDto addSeanceFormation(SeanceFormationDto seanceFormationDto);
}
