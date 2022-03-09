package com.dtos;

import lombok.Data;

import java.util.List;

@Data
public class IntervenantDto extends UtilisateurDto{
    private List<CoursDto> coursDtos;
    private List<SeanceFormationDto> seanceFormationDtos;
}
