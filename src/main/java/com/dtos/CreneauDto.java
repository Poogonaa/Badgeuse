package com.dtos;

import lombok.Data;

@Data
public class CreneauDto {
    private Long id;
    private String date_heure;
    private Integer duree;
    private String type;
    private  String salle;
    private CoursDto coursDto;
}
