package com.services;


import com.dtos.CoursDto;

import java.util.List;

public interface CoursService {

    CoursDto addCours(CoursDto coursDto);

    CoursDto getCoursById(Long coursId);

    boolean deleteCours(Long coursId);

    List<CoursDto> getAllCours();

    CoursDto editCours(CoursDto coursDto);


}
