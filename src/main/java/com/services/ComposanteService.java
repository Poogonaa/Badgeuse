package com.services;

import com.dtos.ComposanteDto;
import com.entities.Composante;

import java.util.List;

public interface ComposanteService {
    ComposanteDto getComposanteById(ComposanteDto composanteDto);

    boolean deleteComposante(ComposanteDto composanteDto);

    List<ComposanteDto> getAllComposantes();

    ComposanteDto editComposante(ComposanteDto composanteDto);

    ComposanteDto addComposante(ComposanteDto composanteDto);

}
