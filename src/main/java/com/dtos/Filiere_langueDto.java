package com.dtos;

import lombok.Data;

import java.util.List;

@Data
public class Filiere_langueDto {
    private Long fil_id;
    private String code;
    private String nom;
    private ComposanteDto composanteDto;
    private List<CoursDto> coursDtos;

    public void addCoursDto(CoursDto coursDto){
        this.coursDtos.add(coursDto);
    }

    public void removeCoursDto(CoursDto coursDto){
        this.coursDtos.remove(coursDto);
    }
}
