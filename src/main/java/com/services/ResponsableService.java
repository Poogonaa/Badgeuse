package com.services;

import com.dtos.GestionnaireDto;
import com.dtos.ResponsableDto;

import java.util.List;

public interface ResponsableService{
    ResponsableDto newResponsable(ResponsableDto responsableDto);

    ResponsableDto getResponsableById(Long id);

    boolean deleteResponsable(Long id);

    List<ResponsableDto> getAllResponsables();

    ResponsableDto editResponsable(ResponsableDto responsableDto);

    ResponsableDto addComposante(ResponsableDto responsableDto);

    ResponsableDto removeComposante(ResponsableDto responsableDto);
}
