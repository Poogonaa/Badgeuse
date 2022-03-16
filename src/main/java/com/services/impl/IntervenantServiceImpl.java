package com.services.impl;

import com.dtos.IntervenantDto;
import com.dtos.UtilisateurDto;
import com.entities.*;
import com.repositories.CoursRepository;
import com.repositories.SeanceFormationRepository;
import com.repositories.UtilisateurRepository;
import com.services.IntervenantService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service("intervenantService")
public class IntervenantServiceImpl extends UtilisateurServiceImpl implements IntervenantService {
    protected final CoursRepository coursRepository;
    protected final SeanceFormationRepository seanceFormationRepository;

    public IntervenantServiceImpl(UtilisateurRepository utilisateurRepository, CoursRepository coursRepository, SeanceFormationRepository seanceFormationRepository){
        super(utilisateurRepository);
        this.coursRepository = coursRepository;
        this.seanceFormationRepository = seanceFormationRepository;
    }

    @Override
    public UtilisateurDto newIntervenant(IntervenantDto utilisateurDto) {
        Intervenant utilisateur = intervenantDtoToEntity(utilisateurDto);
        utilisateur = utilisateurRepository.save(utilisateur);
        return intervenantEntityToDto(utilisateur);
    }

    @Override
    public UtilisateurDto addCours(IntervenantDto utilisateurDto) {
        Intervenant utilisateur = (Intervenant) utilisateurRepository.findById(utilisateurDto.getUti_id()).orElseThrow(() -> new EntityNotFoundException("Intervenant not found"));
        Cours cours = coursRepository.findById(utilisateurDto.getCoursDtos().get(0).getCou_id()).orElseThrow(() -> new EntityNotFoundException("Cours not found"));
        utilisateur.addCours(cours);
        cours.addIntervenant(utilisateur);
        coursRepository.save(cours);
        utilisateur = utilisateurRepository.save(utilisateur);
        return intervenantEntityToDto(utilisateur);
    }

    @Override
    public UtilisateurDto addSeanceFormation(IntervenantDto utilisateurDto) {
        Intervenant utilisateur = (Intervenant) utilisateurRepository.findById(utilisateurDto.getUti_id()).orElseThrow(() -> new EntityNotFoundException("Intervenant not found"));
        SeanceFormation seanceFormation = seanceFormationRepository.findById(utilisateurDto.getSeanceFormationDtos().get(0).getSea_id()).orElseThrow(() -> new EntityNotFoundException("Filiere_langue not found"));
        utilisateur.addSeanceFormation(seanceFormation);
        seanceFormation.setIntervenant(utilisateur);
        seanceFormationRepository.save(seanceFormation);
        utilisateur = utilisateurRepository.save(utilisateur);
        return intervenantEntityToDto(utilisateur);
    }

    @Override
    public UtilisateurDto removeCours(IntervenantDto utilisateurDto) {
        Intervenant utilisateur = (Intervenant) utilisateurRepository.findById(utilisateurDto.getUti_id()).orElseThrow(() -> new EntityNotFoundException("Intervenant not found"));
        Cours cours = coursRepository.findById(utilisateurDto.getCoursDtos().get(0).getCou_id()).orElseThrow(() -> new EntityNotFoundException("Cours not found"));
        utilisateur.removeCours(cours);
        cours.removeIntervenant(utilisateur);
        coursRepository.save(cours);
        utilisateur = utilisateurRepository.save(utilisateur);
        return intervenantEntityToDto(utilisateur);
    }

    @Override
    public UtilisateurDto removeSeanceFormation(IntervenantDto utilisateurDto) {
        Intervenant utilisateur = (Intervenant) utilisateurRepository.findById(utilisateurDto.getUti_id()).orElseThrow(() -> new EntityNotFoundException("Intervenant not found"));
        SeanceFormation seanceFormation = seanceFormationRepository.findById(utilisateurDto.getSeanceFormationDtos().get(0).getSea_id()).orElseThrow(() -> new EntityNotFoundException("Filiere_langue not found"));
        utilisateur.removeSeanceFormation(seanceFormation);
        seanceFormation.setIntervenant(null);
        seanceFormationRepository.save(seanceFormation);
        utilisateur = utilisateurRepository.save(utilisateur);
        return intervenantEntityToDto(utilisateur);
    }

    private Intervenant intervenantDtoToEntity(UtilisateurDto utilisateurDto){
        Intervenant utilisateur = new Intervenant();
        utilisateur.setUti_id(utilisateurDto.getUti_id());
        utilisateur.setLogin(utilisateurDto.getLogin());
        utilisateur.setMdp(utilisateurDto.getMdp());
        utilisateur.setNom(utilisateurDto.getNom());
        utilisateur.setPrenom(utilisateurDto.getPrenom());
        utilisateur.setMail(utilisateurDto.getMail());
        return utilisateur;
    }

    private IntervenantDto intervenantEntityToDto(Utilisateur utilisateur){
        IntervenantDto utilisateurDto = new IntervenantDto();
        utilisateurDto.setUti_id(utilisateur.getUti_id());
        utilisateurDto.setLogin(utilisateur.getLogin());
        utilisateurDto.setMdp(utilisateur.getMdp());
        utilisateurDto.setNom(utilisateur.getNom());
        utilisateurDto.setPrenom(utilisateur.getPrenom());
        utilisateurDto.setMail(utilisateur.getMail());
        return utilisateurDto;
    }
}



