package com.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class Cours {

    @Id
    private Long id;
    private String intitule;
    @OneToMany
    private List<Creneau> mesCreneau;
    @OneToMany
    private List<Vacataire> mesVacataires;

}
