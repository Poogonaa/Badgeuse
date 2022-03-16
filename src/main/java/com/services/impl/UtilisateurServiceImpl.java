package com.services.impl;

import com.dtos.UtilisateurDto;
import com.entities.Utilisateur;
import com.repositories.GestionnaireRepository;
import com.repositories.IntervenantRepository;
import com.repositories.ResponsableRepository;
import com.repositories.UtilisateurRepository;
import com.services.UtilisateurService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service("utilisateurService")
public class UtilisateurServiceImpl implements UtilisateurService {

    protected final UtilisateurRepository utilisateurRepository;

    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository, ResponsableRepository responsableRepository, GestionnaireRepository gestionnaireRepository, IntervenantRepository intervenantRepository) {
        this.utilisateurRepository = utilisateurRepository;
        for(Utilisateur responsable : responsableRepository.findAll()){
            this.utilisateurRepository.save(responsable);
        }
        for(Utilisateur gestionnaire : gestionnaireRepository.findAll()){
            this.utilisateurRepository.save(gestionnaire);
        }
        for(Utilisateur intervenant : intervenantRepository.findAll()){
            this.utilisateurRepository.save(intervenant);
        }
    }

    @Override
    public UtilisateurDto getUtilisateurById(Long id) {
        Utilisateur utilisateur = utilisateurRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Utilisateur not found"));
        return utilisateurEntityToDto(utilisateur);
    }

    @Override
    public List<UtilisateurDto> getAllUtilisateurs() {
        List<UtilisateurDto> utilisateurDtos = new ArrayList<>();
        List<Utilisateur> utilisateurs = utilisateurRepository.findAll();
        for (Utilisateur utilisateur : utilisateurs) {
            utilisateurDtos.add(utilisateurEntityToDto(utilisateur));
        }
        return utilisateurDtos;
    }

    @Override
    public UtilisateurDto connection(UtilisateurDto utilisateurDto){
        List<UtilisateurDto> utilisateurDtos = new ArrayList<>();
        List<Utilisateur> utilisateurs = utilisateurRepository.findAll();
        for (Utilisateur utilisateur : utilisateurs) {
            utilisateurDtos.add(utilisateurEntityToDto(utilisateur));
        }
        for (UtilisateurDto uti : utilisateurDtos){
            if(uti.getLogin().equals(utilisateurDto.getLogin())){
                if(uti.getMdp().equals(utilisateurDto.getMdp())){
                    return uti;
                }
                return null;
            }
        }
        return null;
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
