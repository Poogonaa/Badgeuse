package com.dtos;

import com.entities.Cours;
import com.entities.SeanceFormation;
import lombok.Data;

import java.util.List;

@Data
public class IntervenantDto extends UtilisateurDto{
    private List<CoursDto> coursDtos;
    private List<SeanceFormationDto> seanceFormationDtos;

    public void addCoursDto(CoursDto coursDto) {
        this.coursDtos.add(coursDto);
    }

    public void addSeanceFormationDto(SeanceFormationDto seanceFormationDto){
        this.seanceFormationDtos.add(seanceFormationDto);
    }

    public void removeCoursDto(CoursDto coursDto){
        this.coursDtos.remove(coursDto);
    }

    public void removeSeanceFormationDto(SeanceFormationDto seanceFormationDto){
        this.seanceFormationDtos.remove(seanceFormationDto);
    }
}
