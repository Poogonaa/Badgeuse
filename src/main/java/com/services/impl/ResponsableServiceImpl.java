package com.services.impl;

import com.dtos.ResponsableDto;
import com.dtos.UtilisateurDto;
import com.entities.Responsable;
import com.entities.Utilisateur;
import com.repositories.UtilisateurRepository;
import com.services.ResponsableService;
import org.springframework.stereotype.Service;

@Service("responsableService")
public class ResponsableServiceImpl extends UtilisateurServiceImpl implements ResponsableService {


    public ResponsableServiceImpl(UtilisateurRepository utilisateurRepository) {
        super(utilisateurRepository);
    }

    @Override
    public UtilisateurDto addResponsable(UtilisateurDto utilisateurDto) {
        Utilisateur utilisateur = responsableDtoToEntity(utilisateurDto);
        utilisateur = utilisateurRepository.save(utilisateur);
        return responsableEntityToDto(utilisateur);

    }

    private UtilisateurDto responsableEntityToDto(Utilisateur utilisateur){
        UtilisateurDto utilisateurDto = new ResponsableDto();
        utilisateurDto.setUti_id(utilisateur.getUti_id());
        utilisateur.setLogin(utilisateur.getLogin());
        utilisateurDto.setMdp(utilisateur.getMdp());
        utilisateurDto.setNom(utilisateur.getNom());
        utilisateurDto.setPrenom(utilisateur.getPrenom());
        utilisateurDto.setMail(utilisateur.getMail());
        return utilisateurDto;
    }

    private Utilisateur responsableDtoToEntity(UtilisateurDto utilisateurDto){
        Utilisateur utilisateur = new Responsable();
        utilisateur.setUti_id(utilisateurDto.getUti_id());
        utilisateur.setLogin(utilisateurDto.getLogin());
        utilisateur.setMdp(utilisateurDto.getMdp());
        utilisateur.setNom(utilisateurDto.getNom());
        utilisateur.setPrenom(utilisateurDto.getPrenom());
        utilisateur.setMail(utilisateurDto.getMail());
        return utilisateur;
    }


}
