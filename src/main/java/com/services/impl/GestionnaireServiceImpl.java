package com.services.impl;

import com.dtos.GestionnaireDto;
import com.dtos.UtilisateurDto;
import com.entities.Gestionnaire;
import com.entities.Utilisateur;
import com.repositories.UtilisateurRepository;
import com.services.GestionnaireService;
import org.springframework.stereotype.Service;

@Service("gestionnaireService")
public class GestionnaireServiceImpl extends UtilisateurServiceImpl implements GestionnaireService {

    public GestionnaireServiceImpl(UtilisateurRepository utilisateurRepository){
        super(utilisateurRepository);
    }

    @Override
    public UtilisateurDto newGestionnaire(GestionnaireDto utilisateurDto) {
        Utilisateur utilisateur = gestionnaireDtoToEntity(utilisateurDto);
        utilisateur = utilisateurRepository.save(utilisateur);
        return gestionnaireEntityToDto(utilisateur);
    }

    private Gestionnaire gestionnaireDtoToEntity(UtilisateurDto utilisateurDto){
        Gestionnaire utilisateur = new Gestionnaire();
        utilisateur.setUti_id(utilisateurDto.getUti_id());
        utilisateur.setLogin(utilisateurDto.getLogin());
        utilisateur.setMdp(utilisateurDto.getMdp());
        utilisateur.setNom(utilisateurDto.getNom());
        utilisateur.setPrenom(utilisateurDto.getPrenom());
        utilisateur.setMail(utilisateurDto.getMail());
        return utilisateur;
    }

    private GestionnaireDto gestionnaireEntityToDto(Utilisateur utilisateur){
        GestionnaireDto utilisateurDto = new GestionnaireDto();
        utilisateurDto.setUti_id(utilisateur.getUti_id());
        utilisateurDto.setLogin(utilisateur.getLogin());
        utilisateurDto.setMdp(utilisateur.getMdp());
        utilisateurDto.setNom(utilisateur.getNom());
        utilisateurDto.setPrenom(utilisateur.getPrenom());
        utilisateurDto.setMail(utilisateur.getMail());
        return utilisateurDto;
    }
}
