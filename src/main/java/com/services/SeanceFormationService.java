package com.services;

import com.dtos.CreneauDto;
import com.dtos.IntervenantDto;
import com.dtos.SeanceFormationDto;

import java.util.List;

public interface SeanceFormationService {
    SeanceFormationDto getSeanceFormationById(Long id);

    boolean deleteSeanceFormation(Long id);

    List<SeanceFormationDto> getAllSeancesFormations();

    SeanceFormationDto editSeanceFormation(SeanceFormationDto seanceFromationDto);

    SeanceFormationDto newSeanceFormation(SeanceFormationDto seanceFormationDto);

    SeanceFormationDto addIntervenant(SeanceFormationDto seanceFormationDto);

    SeanceFormationDto addCreneau(SeanceFormationDto seanceFormationDto);

    SeanceFormationDto removeIntervenant(SeanceFormationDto seanceFormationDto);

    SeanceFormationDto removeCreneau(SeanceFormationDto seanceFormationDto);
}
