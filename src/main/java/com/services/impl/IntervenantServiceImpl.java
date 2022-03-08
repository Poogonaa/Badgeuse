package com.services.impl;

import com.dtos.IntervenantDto;
import com.dtos.UtilisateurDto;
import com.entities.Intervenant;
import com.entities.Utilisateur;
import com.repositories.UtilisateurRepository;
import com.services.IntervenantService;
import org.springframework.stereotype.Service;

@Service("intervenantService")
public class IntervenantServiceImpl extends UtilisateurServiceImpl implements IntervenantService {

    public IntervenantServiceImpl(UtilisateurRepository utilisateurRepository){
        super(utilisateurRepository);
    }

    @Override
    public UtilisateurDto addIntervenant(UtilisateurDto utilisateurDto) {
        Utilisateur utilisateur = intervenantDtoToEntity(utilisateurDto);
        utilisateur = utilisateurRepository.save(utilisateur);
        return intervenantEntityToDto(utilisateur);
    }

    private Utilisateur intervenantDtoToEntity(UtilisateurDto utilisateurDto){
        Utilisateur utilisateur = new Intervenant();
        utilisateur.setId(utilisateurDto.getId());
        utilisateur.setLogin(utilisateurDto.getLogin());
        utilisateur.setMdp(utilisateurDto.getMdp());
        utilisateur.setNom(utilisateurDto.getNom());
        utilisateur.setPrenom(utilisateurDto.getPrenom());
        utilisateur.setMail(utilisateurDto.getMail());
        return utilisateur;
    }

    private UtilisateurDto intervenantEntityToDto(Utilisateur utilisateur){
        UtilisateurDto utilisateurDto = new IntervenantDto();
        utilisateurDto.setId(utilisateur.getId());
        utilisateurDto.setLogin(utilisateur.getLogin());
        utilisateurDto.setMdp(utilisateur.getMdp());
        utilisateurDto.setNom(utilisateur.getNom());
        utilisateurDto.setPrenom(utilisateur.getPrenom());
        utilisateurDto.setMail(utilisateur.getMail());
        return utilisateurDto;
    }
}



