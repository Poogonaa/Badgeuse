package com.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Intervenant extends Utilisateur{
    @ManyToMany
    private List<Cours> cours;
}
