package com.services.impl;

import com.dtos.GestionnaireDto;
import com.dtos.UtilisateurDto;
import com.entities.Gestionnaire;
import com.entities.Utilisateur;
import com.repositories.GestionnaireRepository;
import com.repositories.UtilisateurRepository;
import com.services.GestionnaireService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service("gestionnaireService")
public class GestionnaireServiceImpl implements GestionnaireService {

    GestionnaireRepository gestionnaireRepository;

    public GestionnaireServiceImpl(GestionnaireRepository gestionnaireRepository){
        this.gestionnaireRepository = gestionnaireRepository;
    }

    @Override
    public GestionnaireDto newGestionnaire(GestionnaireDto gestionnaireDto) {
        Gestionnaire gestionnaire = gestionnaireDtoToEntity(gestionnaireDto);
        gestionnaire = gestionnaireRepository.save(gestionnaire);
        return gestionnaireEntityToDto(gestionnaire);
    }

    @Override
    public GestionnaireDto getGestionnaireById(Long id) {
        Gestionnaire gestionnaire = gestionnaireRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Gestionnaire not found"));
        return gestionnaireEntityToDto(gestionnaire);
    }

    @Override
    public boolean deleteGestionnaire(Long id) {
        gestionnaireRepository.deleteById(id);
        return true;
    }

    @Override
    public List<GestionnaireDto> getAllGestionnaires() {
        List<GestionnaireDto> gestionnaireDtos = new ArrayList<>();
        List<Gestionnaire> gestionnaires = gestionnaireRepository.findAll();
        for (Gestionnaire gestionnaire : gestionnaires) {
            gestionnaireDtos.add(gestionnaireEntityToDto(gestionnaire));
        }
        return gestionnaireDtos;
    }

    @Override
    public GestionnaireDto editGestionnaire(GestionnaireDto gestionnaireDto) {
        Gestionnaire gestionnaire = gestionnaireRepository.findById(gestionnaireDto.getUti_id()).orElseThrow(() -> new EntityNotFoundException("Utilisateur not found"));
        gestionnaire.setLogin(gestionnaireDto.getLogin());
        gestionnaire.setMdp(gestionnaireDto.getMdp());
        gestionnaire.setNom(gestionnaireDto.getNom());
        gestionnaire.setPrenom(gestionnaireDto.getPrenom());
        gestionnaire.setMail(gestionnaireDto.getMail());
        gestionnaire.setDtype(gestionnaireDto.getDtype());
        gestionnaire = gestionnaireRepository.save(gestionnaire);
        return gestionnaireEntityToDto(gestionnaire);
    }

    private Gestionnaire gestionnaireDtoToEntity(GestionnaireDto gestionnaireDto){
        Gestionnaire gestionnaire = new Gestionnaire();
        gestionnaire.setUti_id(gestionnaireDto.getUti_id());
        gestionnaire.setLogin(gestionnaireDto.getLogin());
        gestionnaire.setMdp(gestionnaireDto.getMdp());
        gestionnaire.setNom(gestionnaireDto.getNom());
        gestionnaire.setPrenom(gestionnaireDto.getPrenom());
        gestionnaire.setMail(gestionnaireDto.getMail());
        gestionnaire.setDtype(gestionnaireDto.getDtype());
        return gestionnaire;
    }

    private GestionnaireDto gestionnaireEntityToDto(Gestionnaire gestionnaire){
        GestionnaireDto gestionnaireDto = new GestionnaireDto();
        gestionnaireDto.setUti_id(gestionnaire.getUti_id());
        gestionnaireDto.setLogin(gestionnaire.getLogin());
        gestionnaireDto.setMdp(gestionnaire.getMdp());
        gestionnaireDto.setNom(gestionnaire.getNom());
        gestionnaireDto.setPrenom(gestionnaire.getPrenom());
        gestionnaireDto.setMail(gestionnaire.getMail());
        gestionnaireDto.setDtype(gestionnaire.getDtype());
        return gestionnaireDto;
    }
}
