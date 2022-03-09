package com.dtos;

import lombok.Data;

import java.util.List;

@Data
public class CoursDto {
    private Long cou_id;
    private String intitule;
    private List<IntervenantDto> IntervenantDtos;
    private List<Filiere_langueDto> filiere_langueDtos;
    private List<CreneauDto> creneauDtos;
}