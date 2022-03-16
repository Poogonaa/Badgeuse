package com.dtos;

import lombok.Data;

import java.util.List;

@Data
public class ComposanteDto {
    private Long com_id;
    private String nomComposante;
    private List<Filiere_langueDto> filiere_langueDtos;
    private List<ResponsableDto> responsableDtos;

    public void addFiliere_langueDto(Filiere_langueDto filiere_langueDto){
        this.filiere_langueDtos.add(filiere_langueDto);
    }

    public void addResponsableDto(ResponsableDto responsableDto){
        this.responsableDtos.add(responsableDto);
    }

    public void removeFiliere_langueDto(Filiere_langueDto filiere_langueDto){
        this.filiere_langueDtos.remove(filiere_langueDto);
    }

    public void removeResponsableDto(ResponsableDto responsableDto){
        this.responsableDtos.remove(responsableDto);
    }
}
