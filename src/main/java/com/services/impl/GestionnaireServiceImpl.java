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
    public UtilisateurDto addGestionnaire(UtilisateurDto utilisateurDto) {
        Utilisateur utilisateur = gestionnaireDtoToEntity(utilisateurDto);
        utilisateur = utilisateurRepository.save(utilisateur);
        return gestionnaireEntityToDto(utilisateur);
    }

    private Utilisateur gestionnaireDtoToEntity(UtilisateurDto utilisateurDto){
        Utilisateur utilisateur = new Gestionnaire();
        utilisateur.setId(utilisateurDto.getId());
        utilisateur.setLogin(utilisateurDto.getLogin());
        utilisateur.setMdp(utilisateurDto.getMdp());
        utilisateur.setNom(utilisateurDto.getNom());
        utilisateur.setPrenom(utilisateurDto.getPrenom());
        utilisateur.setMail(utilisateurDto.getMail());
        return utilisateur;
    }

    private UtilisateurDto gestionnaireEntityToDto(Utilisateur utilisateur){
        UtilisateurDto utilisateurDto = new GestionnaireDto();
        utilisateurDto.setId(utilisateur.getId());
        utilisateurDto.setLogin(utilisateur.getLogin());
        utilisateurDto.setMdp(utilisateur.getMdp());
        utilisateurDto.setNom(utilisateur.getNom());
        utilisateurDto.setPrenom(utilisateur.getPrenom());
        utilisateurDto.setMail(utilisateur.getMail());
        return utilisateurDto;
    }
}
