package com.services;

import com.dtos.ComposanteDto;
import com.entities.Composante;

import java.util.List;

public interface ComposanteService {
    ComposanteDto getComposanteById(Long id);

    boolean deleteComposante(Long id);

    List<ComposanteDto> getAllComposantes();

    ComposanteDto editComposante(ComposanteDto composanteDto);

    ComposanteDto addComposante(ComposanteDto composanteDto);

}
