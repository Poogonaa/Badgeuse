package com.services.impl;

import com.dtos.UtilisateurDto;
import com.entities.Utilisateur;
import com.repositories.UtilisateurRepository;
import com.services.IntervenantService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service("intervenantService")
public class IntervenantServiceImpl implements IntervenantService {

    private final UtilisateurRepository utilisateurRepository;

    public IntervenantServiceImpl(UtilisateurRepository utilisateurRepository){
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public UtilisateurDto addIntervenant(UtilisateurDto utilisateurDto) {
        Utilisateur utilisateur = intervenantDtoToEntity(utilisateurDto);
        utilisateur = utilisateurRepository.save(utilisateur);
        return intervenantEntityToDto(utilisateur);
    }

    @Override
    public UtilisateurDto getIntervenantById(Long intervenantId) {
        Utilisateur utilisateur = utilisateurRepository.findById(intervenantId).orElseThrow(() -> new EntityNotFoundException("Utilisateur not found"));
        return intervenantEntityToDto(utilisateur);
    }

    @Override
    public boolean deleteIntervenant(Long intervenantId) {
        utilisateurRepository.deleteById(intervenantId);
        return true;
    }

    @Override
    public List<UtilisateurDto> getAllIntervenants() {
        List<UtilisateurDto> utilisateurDtos = new ArrayList<>();
        List<Utilisateur> utilisateurs = utilisateurRepository.findAll();
        utilisateurs.forEach(utilisateur -> {
            utilisateurDtos.add(intervenantEntityToDto(utilisateur));
        });
        return utilisateurDtos;
    }

    private Utilisateur intervenantDtoToEntity(UtilisateurDto utilisateurDto){
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(utilisateurDto.getId());
        utilisateur.setLogin(utilisateurDto.getLogin());
        utilisateur.setMdp(utilisateurDto.getMdp());
        utilisateur.setNom(utilisateurDto.getNom());
        utilisateur.setPrenom(utilisateurDto.getPrenom());
        utilisateur.setMail(utilisateurDto.getMail());
        return utilisateur;
    }

    private UtilisateurDto intervenantEntityToDto(Utilisateur utilisateur){
        UtilisateurDto utilisateurDto = new UtilisateurDto();
        utilisateurDto.setId(utilisateur.getId());
        utilisateurDto.setLogin(utilisateur.getLogin());
        utilisateurDto.setMdp(utilisateur.getMdp());
        utilisateurDto.setNom(utilisateur.getNom());
        utilisateurDto.setPrenom(utilisateur.getPrenom());
        utilisateurDto.setMail(utilisateur.getMail());
        return utilisateurDto;
    }
}



