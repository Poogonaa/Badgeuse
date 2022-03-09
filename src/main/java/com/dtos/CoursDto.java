package com.dtos;

import lombok.Data;

import java.util.List;

@Data
public class CoursDto {

    private Long id;
    private String intitule;
    private List<CreneauDto> CreneauxDto;
    private List<IntervenantDto> IntervenantsDto;
}
