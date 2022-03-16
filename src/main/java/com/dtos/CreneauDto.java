package com.dtos;

import lombok.Data;

import java.util.List;

@Data
public class CreneauDto {
    private Long cre_id;
    private String heure_debut;
    private String date;
    private Integer duree;
    private String type;
    private String salle;
    private CoursDto coursDto;
    private List<SeanceFormationDto> seanceFormationDtos;
}
