package com.dtos;

import lombok.Data;

import java.util.List;

@Data
public class ComposanteDto {
    private Long id;
    private String nomComposante;
    private List<Filiere_langueDto> filieres_languesDto;
}
