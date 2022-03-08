package com.services;

import com.dtos.CreneauDto;

import java.util.List;

public interface CreneauService {
    CreneauDto addCreneau(CreneauDto creneauDto);

    CreneauDto getCreneauById(CreneauDto creneauDto);

    boolean deleteCreneau(CreneauDto creneauDto);

    List<CreneauDto> getAllCreneaux();

    CreneauDto editCreneau(CreneauDto creneauDto);
}
