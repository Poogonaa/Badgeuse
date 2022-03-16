package com.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Composante {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long com_id;
    private String nomComposante;

    @OneToMany (targetEntity = Filiere_langue.class, mappedBy = "composante" )
    private List<Filiere_langue> filiere_langues;

    @OneToMany (targetEntity = Responsable.class, mappedBy = "composante" )
    private List<Responsable> responsables;

    public void addFiliere_langue(Filiere_langue filiere_langue){
        this.filiere_langues.add(filiere_langue);
    }

    public void addResponsable(Responsable responsable){
        this.responsables.add(responsable);
    }

    public void removeFiliere_langue(Filiere_langue filiere_langue){
        this.filiere_langues.remove(filiere_langue);
    }

    public void removeResponsable(Responsable responsable){
        this.responsables.remove(responsable);
    }
}
