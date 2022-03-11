package com.services;

import com.dtos.CreneauDto;

import java.util.List;

public interface CreneauService {
    CreneauDto addCreneau(CreneauDto creneauDto);

    CreneauDto getCreneauById(Long id);

    boolean deleteCreneau(Long id);

    List<CreneauDto> getAllCreneaux();

    CreneauDto editCreneau(CreneauDto creneauDto);
}
