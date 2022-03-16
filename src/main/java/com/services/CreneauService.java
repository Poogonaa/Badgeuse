package com.services;

import com.dtos.CoursDto;
import com.dtos.CreneauDto;
import com.dtos.SeanceFormationDto;

import java.util.List;

public interface CreneauService {
    CreneauDto newCreneau(CreneauDto creneauDto);

    CreneauDto getCreneauById(Long id);

    boolean deleteCreneau(Long id);

    List<CreneauDto> getAllCreneaux();

    CreneauDto editCreneau(CreneauDto creneauDto);

    CreneauDto addCours(CreneauDto creneauDto);

    CreneauDto addSeanceFormation(CreneauDto creneauDto);

    CreneauDto removeCours(CreneauDto creneauDto);

    CreneauDto removeSeanceFormation(CreneauDto creneauDto);
}
