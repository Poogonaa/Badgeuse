package com.dtos;

import lombok.Data;

import java.util.List;

@Data
public class CoursDto {
    private Long cou_id;
    private String intitule;
    private List<IntervenantDto> intervenantDtos;
    private List<Filiere_langueDto> filiere_langueDtos;
    private List<CreneauDto> creneauDtos;

    public void addIntervenantDto(IntervenantDto intervenantDto){
        this.intervenantDtos.add(intervenantDto);
    }

    public void addFiliere_langueDto(Filiere_langueDto filiere_langueDto){
        this.filiere_langueDtos.add(filiere_langueDto);
    }

    public void addCreneauDto(CreneauDto creneauDto){
        this.creneauDtos.add(creneauDto);
    }

    public void removeIntervenantDto(IntervenantDto intervenantDto){
        this.intervenantDtos.remove(intervenantDto);
    }

    public void removeFiliere_langueDto(Filiere_langueDto filiere_langueDto){
        this.filiere_langueDtos.remove(filiere_langueDto);
    }

    public void removeCreneauDto(CreneauDto creneauDto){
        this.creneauDtos.remove(creneauDto);
    }
}
