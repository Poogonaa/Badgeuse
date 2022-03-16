package com.services.impl;

import com.dtos.ResponsableDto;
import com.dtos.UtilisateurDto;
import com.entities.*;
import com.repositories.ComposanteRepository;
import com.repositories.UtilisateurRepository;
import com.services.ResponsableService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service("responsableService")
public class ResponsableServiceImpl extends UtilisateurServiceImpl implements ResponsableService {
    protected final ComposanteRepository composanteRepository;

    public ResponsableServiceImpl(UtilisateurRepository utilisateurRepository, ComposanteRepository composanteRepository) {
        super(utilisateurRepository);
        this.composanteRepository = composanteRepository;
    }

    @Override
    public UtilisateurDto newResponsable(ResponsableDto utilisateurDto) {
        Responsable utilisateur = responsableDtoToEntity(utilisateurDto);
        utilisateur = utilisateurRepository.save(utilisateur);
        return responsableEntityToDto(utilisateur);

    }

    @Override
    public UtilisateurDto addComposante(ResponsableDto utilisateurDto) {
        Responsable utilisateur = (Responsable) utilisateurRepository.findById(utilisateurDto.getUti_id()).orElseThrow(() -> new EntityNotFoundException("Composante not found"));
        Composante composante = composanteRepository.findById(utilisateurDto.getComposanteDto().getCom_id()).orElseThrow(() -> new EntityNotFoundException("Composante not found"));
        utilisateur.setComposante(composante);
        composante.addResponsable(utilisateur);
        composanteRepository.save(composante);
        utilisateur = utilisateurRepository.save(utilisateur);
        return responsableEntityToDto(utilisateur);
    }

    @Override
    public UtilisateurDto removeComposante(ResponsableDto utilisateurDto) {
        Responsable utilisateur = (Responsable) utilisateurRepository.findById(utilisateurDto.getUti_id()).orElseThrow(() -> new EntityNotFoundException("Composante not found"));
        Composante composante = composanteRepository.findById(utilisateurDto.getComposanteDto().getCom_id()).orElseThrow(() -> new EntityNotFoundException("Composante not found"));
        utilisateur.setComposante(null);
        composante.removeResponsable(utilisateur);
        composanteRepository.save(composante);
        utilisateur = utilisateurRepository.save(utilisateur);
        return responsableEntityToDto(utilisateur);
    }

    private ResponsableDto responsableEntityToDto(Utilisateur utilisateur){
        ResponsableDto utilisateurDto = new ResponsableDto();
        utilisateurDto.setUti_id(utilisateur.getUti_id());
        utilisateur.setLogin(utilisateur.getLogin());
        utilisateurDto.setMdp(utilisateur.getMdp());
        utilisateurDto.setNom(utilisateur.getNom());
        utilisateurDto.setPrenom(utilisateur.getPrenom());
        utilisateurDto.setMail(utilisateur.getMail());
        return utilisateurDto;
    }

    private Responsable responsableDtoToEntity(UtilisateurDto utilisateurDto){
        Responsable utilisateur = new Responsable();
        utilisateur.setUti_id(utilisateurDto.getUti_id());
        utilisateur.setLogin(utilisateurDto.getLogin());
        utilisateur.setMdp(utilisateurDto.getMdp());
        utilisateur.setNom(utilisateurDto.getNom());
        utilisateur.setPrenom(utilisateurDto.getPrenom());
        utilisateur.setMail(utilisateurDto.getMail());
        return utilisateur;
    }
}
