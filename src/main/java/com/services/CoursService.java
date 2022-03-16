package com.services;


import com.dtos.CoursDto;
import com.dtos.CreneauDto;
import com.dtos.Filiere_langueDto;
import com.dtos.IntervenantDto;
import com.entities.Cours;

import java.util.List;

public interface CoursService {
    CoursDto newCours(CoursDto coursDto);

    CoursDto getCoursById(Long id);

    boolean deleteCours(Long id);

    List<CoursDto> getAllCours();

    CoursDto editCours(CoursDto coursDto);

    CoursDto addIntervenant(CoursDto coursDto);

    CoursDto addFiliere_langue(CoursDto coursDto);

    CoursDto addCreneau(CoursDto coursDto);

    CoursDto removeIntervenant(CoursDto coursDto);

    CoursDto removeFiliere_langue(CoursDto coursDto);

    CoursDto removeCreneau(CoursDto coursDto);
}
