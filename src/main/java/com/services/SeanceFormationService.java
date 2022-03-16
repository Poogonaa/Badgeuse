package com.services;

import com.dtos.SeanceFormationDto;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface SeanceFormationService {
    SeanceFormationDto getSeanceFormationById(Long id);

    boolean deleteSeanceFormation(Long id);

    List<SeanceFormationDto> getAllSeancesFormations();

    List<SeanceFormationDto> getAllSeancesFormationsByIntervenant(Long id);

    List<SeanceFormationDto> getAllSeancesFormationsEffectueesByIntervenant(Long id);

    List<SeanceFormationDto> getAllSeancesFormationsValide();

    SeanceFormationDto editSeanceFormation(SeanceFormationDto seanceFromationDto);

    SeanceFormationDto addSeanceFormation(SeanceFormationDto seanceFormationDto);

    SeanceFormationDto valider(SeanceFormationDto seanceFormationDto);

}
