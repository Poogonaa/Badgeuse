package com.services;

import com.dtos.CreneauDto;
import com.dtos.IntervenantDto;
import com.dtos.SeanceFormationDto;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

public interface SeanceFormationService {
    SeanceFormationDto getSeanceFormationById(Long id);

    boolean deleteSeanceFormation(Long id);

    List<SeanceFormationDto> getAllSeancesFormations();

    List<SeanceFormationDto> getAllSeancesFormationsByIntervenant(Long id);

    List<SeanceFormationDto> getAllSeancesFormationsEffectueesByIntervenant(Long id);

    List<SeanceFormationDto> getAllSeancesFormationsValide();

    Map<Long, Integer> getHeureIntervenants();

    SeanceFormationDto editSeanceFormation(SeanceFormationDto seanceFromationDto);

    SeanceFormationDto newSeanceFormation(SeanceFormationDto seanceFormationDto);

    SeanceFormationDto addIntervenant(SeanceFormationDto seanceFormationDto);

    SeanceFormationDto addCreneau(SeanceFormationDto seanceFormationDto);

    SeanceFormationDto removeIntervenant(SeanceFormationDto seanceFormationDto);

    SeanceFormationDto removeCreneau(SeanceFormationDto seanceFormationDto);

    SeanceFormationDto valider(SeanceFormationDto seanceFormationDto);

}
