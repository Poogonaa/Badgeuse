package com.dtos;

public class SeanceFormationDto {

    private Long id;
    private Boolean estEffectue;
    private int dureeEffective;
    private Boolean valide;
    private String commentaire;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getEstEffectue() {
        return estEffectue;
    }

    public void setEstEffectue(Boolean estEffectue) {
        this.estEffectue = estEffectue;
    }

    public int getDureeEffective() {
        return dureeEffective;
    }

    public void setDureeEffective(int dureeEffective) {
        this.dureeEffective = dureeEffective;
    }

    public Boolean getValide() {
        return valide;
    }

    public void setValide(Boolean valide) {
        this.valide = valide;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

}
