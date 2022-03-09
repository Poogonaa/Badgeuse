package com.dtos;

import lombok.Data;

@Data
public class SeanceFormationDto {
    private Long sea_id;
    private Boolean estEffectue;
    private int dureeEffective;
    private Boolean valide;
    private String commentaire;
    private IntervenantDto intervenantDto;
    private CreneauDto creneauDto;
}
