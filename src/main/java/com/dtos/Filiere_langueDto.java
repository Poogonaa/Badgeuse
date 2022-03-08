package com.dtos;

import lombok.Data;

import javax.persistence.ManyToMany;
import java.util.List;

@Data
public class Filiere_langueDto {
    private Long id;
    private String code;
    private String nom;
    private ComposanteDto composanteDto;
    private List<CoursDto> coursDto;
}
