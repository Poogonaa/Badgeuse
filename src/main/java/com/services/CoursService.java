package com.services;


import com.dtos.CoursDto;

import java.util.List;

public interface CoursService {
    CoursDto addCours(CoursDto coursDto);

    CoursDto getCoursById(Long id);

    boolean deleteCours(Long id);

    List<CoursDto> getAllCours();

    CoursDto editCours(CoursDto coursDto);
}
