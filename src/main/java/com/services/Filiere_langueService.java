package com.services;

import com.dtos.ComposanteDto;
import com.dtos.CoursDto;
import com.dtos.Filiere_langueDto;

import java.util.List;

public interface Filiere_langueService {
    Filiere_langueDto newFiliere_langue(Filiere_langueDto filiere_langueDto);

    Filiere_langueDto getFiliere_langueById(Long id);

    boolean deleteFiliere_langue(Long id);

    List<Filiere_langueDto> getAllFiliere_langues();

    Filiere_langueDto editFiliere_langue(Filiere_langueDto filiere_langueDto);

    Filiere_langueDto addComposante(Filiere_langueDto filiere_langueDto);

    Filiere_langueDto addCours(Filiere_langueDto filiere_langueDto);

    Filiere_langueDto removeComposante(Filiere_langueDto filiere_langueDto);

    Filiere_langueDto removeCours(Filiere_langueDto filiere_langueDto);
}
