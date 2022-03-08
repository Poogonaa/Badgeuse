package com.services;


import com.dtos.CoursDto;

import java.util.List;

public interface CoursService {
    CoursDto addCours(CoursDto coursDto);

    CoursDto getCoursById(CoursDto coursDto);

    boolean deleteCours(CoursDto coursDto);

    List<CoursDto> getAllCours();

    CoursDto editCours(CoursDto coursDto);
}
