package com.services;

import com.dtos.ComposanteDto;
import com.dtos.Filiere_langueDto;
import com.dtos.ResponsableDto;
import com.entities.Responsable;

import java.util.List;

public interface ComposanteService {
    ComposanteDto getComposanteById(Long id);

    boolean deleteComposante(Long id);

    List<ComposanteDto> getAllComposantes();

    ComposanteDto editComposante(ComposanteDto composanteDto);

    ComposanteDto newComposante(ComposanteDto composanteDto);

    ComposanteDto addFiliere_langue(ComposanteDto composanteDto);

    ComposanteDto addResponsable(ComposanteDto composanteDto);

    ComposanteDto removeFiliere_langue(ComposanteDto composanteDto);

    ComposanteDto removeResponsable(ComposanteDto composanteDto);
}
