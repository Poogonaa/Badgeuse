package com.services;

import com.dtos.Filiere_langueDto;

import java.util.List;

public interface Filiere_langueService {
    Filiere_langueDto addFiliere_langue(Filiere_langueDto filiere_langueDto);

    Filiere_langueDto getFiliere_langueById(Filiere_langueDto filiere_langueDto);

    boolean deleteFiliere_langue(Filiere_langueDto filiere_langueDto);

    List<Filiere_langueDto> getAllFiliere_langues();

    Filiere_langueDto editFiliere_langue(Filiere_langueDto filiere_langueDto);
}
