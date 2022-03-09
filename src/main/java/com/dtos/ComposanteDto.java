package com.dtos;

import lombok.Data;

import java.util.List;

@Data
public class ComposanteDto {
    private Long com_id;
    private String nomComposante;
    private List<Filiere_langueDto> filiere_langueDtos;
    private List<ResponsableDto> responsableDtos;
}
