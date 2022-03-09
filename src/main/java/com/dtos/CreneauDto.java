package com.dtos;

import lombok.Data;

import java.util.List;

@Data
public class CreneauDto {
    private Long cre_id;
    private String date_heure;
    private Integer duree;
    private String type;
    private  String salle;
    private CoursDto coursDto;
    private List<SeanceFormationDto> seanceFormationDtos;
}
