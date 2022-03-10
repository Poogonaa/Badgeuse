package com.services.impl;

import com.dtos.UtilisateurDto;
import com.entities.Utilisateur;
import com.repositories.UtilisateurRepository;
import com.services.UtilisateurService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service("utilisateurService")
public class UtilisateurServiceImpl implements UtilisateurService {

    protected final UtilisateurRepository utilisateurRepository;

    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public UtilisateurDto getUtilisateurById(Long id) {
        Utilisateur utilisateur = utilisateurRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Utilisateur not found"));
        return utilisateurEntityToDto(utilisateur);
    }

    @Override
    public boolean deleteUtilisateur(Long id) {
        utilisateurRepository.deleteById(id);
        return true;
    }

    @Override
    public List<UtilisateurDto> getAllUtilisateurs() {
        List<UtilisateurDto> utilisateurDtos = new ArrayList<>();
        List<Utilisateur> utilisateurs = utilisateurRepository.findAll();
        utilisateurs.forEach(utilisateur -> {
            utilisateurDtos.add(utilisateurEntityToDto(utilisateur));
        });
        return utilisateurDtos;
    }

    @Override
    public UtilisateurDto editUtilisateur(UtilisateurDto utilisateurDto){
        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurDto.getUti_id()).orElseThrow(() -> new EntityNotFoundException("Utilisateur not found"));
        utilisateur.setLogin(utilisateurDto.getLogin());
        utilisateur.setMdp(utilisateurDto.getMdp());
        utilisateur.setNom(utilisateurDto.getNom());
        utilisateur.setPrenom(utilisateurDto.getPrenom());
        utilisateur.setMail(utilisateurDto.getMail());
        utilisateur.setDtype(utilisateurDto.getDtype());
        utilisateur = utilisateurRepository.save(utilisateur);
        return utilisateurEntityToDto(utilisateur);
    }

    private UtilisateurDto utilisateurEntityToDto(Utilisateur utilisateur){
        UtilisateurDto utilisateurDto = new UtilisateurDto();
        utilisateurDto.setUti_id(utilisateur.getUti_id());
        utilisateurDto.setLogin(utilisateur.getLogin());
        utilisateurDto.setMdp(utilisateur.getMdp());
        utilisateurDto.setNom(utilisateur.getNom());
        utilisateurDto.setPrenom(utilisateur.getPrenom());
        utilisateurDto.setMail(utilisateur.getMail());
        utilisateurDto.setDtype(utilisateur.getDtype());
        return utilisateurDto;
    }
}
