package com.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Responsable extends Utilisateur{
    @ManyToOne @JoinColumn( name = "com_id" )
    private Composante composante;
}
