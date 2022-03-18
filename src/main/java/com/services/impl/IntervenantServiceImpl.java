package com.services.impl;

import com.dtos.*;
import com.entities.*;
import com.repositories.CoursRepository;
import com.repositories.IntervenantRepository;
import com.repositories.SeanceFormationRepository;
import com.repositories.UtilisateurRepository;
import com.services.IntervenantService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service("intervenantService")
public class IntervenantServiceImpl implements IntervenantService {
    protected final IntervenantRepository intervenantRepository;
    protected final CoursRepository coursRepository;
    protected final SeanceFormationRepository seanceFormationRepository;

    public IntervenantServiceImpl(IntervenantRepository intervenantRepository, CoursRepository coursRepository, SeanceFormationRepository seanceFormationRepository){
        this.intervenantRepository = intervenantRepository;
        this.coursRepository = coursRepository;
        this.seanceFormationRepository = seanceFormationRepository;
    }

    @Override
    public IntervenantDto newIntervenant(IntervenantDto intervenantDto) {
        Intervenant intervenant = intervenantDtoToEntity(intervenantDto);
        intervenant = intervenantRepository.save(intervenant);
        return intervenantEntityToDto(intervenant);
    }

    @Override
    public IntervenantDto addCours(IntervenantDto intervenantDto) {
        Intervenant intervenant = intervenantRepository.findById(intervenantDto.getUti_id()).orElseThrow(() -> new EntityNotFoundException("Intervenant not found"));
        Cours cours = coursRepository.findById(intervenantDto.getCoursDtos().get(0).getCou_id()).orElseThrow(() -> new EntityNotFoundException("Cours not found"));
        intervenant.addCours(cours);
        intervenant = intervenantRepository.save(intervenant);
        return intervenantEntityToDto(intervenant);
    }

    @Override
    public IntervenantDto addSeanceFormation(IntervenantDto intervenantDto) {
        Intervenant intervenant = intervenantRepository.findById(intervenantDto.getUti_id()).orElseThrow(() -> new EntityNotFoundException("Intervenant not found"));
        SeanceFormation seanceFormation = seanceFormationRepository.findById(intervenantDto.getSeanceFormationDtos().get(0).getSea_id()).orElseThrow(() -> new EntityNotFoundException("Filiere_langue not found"));
        intervenant.addSeanceFormation(seanceFormation);
        seanceFormation.setIntervenant(intervenant);
        seanceFormationRepository.save(seanceFormation);
        intervenant = intervenantRepository.save(intervenant);
        return intervenantEntityToDto(intervenant);
    }

    @Override
    public IntervenantDto removeCours(IntervenantDto intervenantDto) {
        Intervenant intervenant = intervenantRepository.findById(intervenantDto.getUti_id()).orElseThrow(() -> new EntityNotFoundException("Intervenant not found"));
        Cours cours = coursRepository.findById(intervenantDto.getCoursDtos().get(0).getCou_id()).orElseThrow(() -> new EntityNotFoundException("Cours not found"));
        intervenant.removeCours(cours);
        intervenant = intervenantRepository.save(intervenant);
        return intervenantEntityToDto(intervenant);
    }

    @Override
    public IntervenantDto removeSeanceFormation(IntervenantDto intervenantDto) {
        Intervenant intervenant = intervenantRepository.findById(intervenantDto.getUti_id()).orElseThrow(() -> new EntityNotFoundException("Intervenant not found"));
        SeanceFormation seanceFormation = seanceFormationRepository.findById(intervenantDto.getSeanceFormationDtos().get(0).getSea_id()).orElseThrow(() -> new EntityNotFoundException("Filiere_langue not found"));
        intervenant.removeSeanceFormation(seanceFormation);
        seanceFormation.setIntervenant(null);
        seanceFormationRepository.save(seanceFormation);
        intervenant = intervenantRepository.save(intervenant);
        return intervenantEntityToDto(intervenant);
    }

    @Override
    public boolean deleteIntervenant(Long id) {
        intervenantRepository.deleteById(id);
        return true;
    }

    @Override
    public IntervenantDto getIntervenantById(Long id) {
        Intervenant intervenant = intervenantRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Intervenant not found"));
        return intervenantEntityToDto(intervenant);
    }

    @Override
    public IntervenantDto editIntervenant(IntervenantDto intervenantDto) {
        Intervenant intervenant = intervenantRepository.findById(intervenantDto.getUti_id()).orElseThrow(() -> new EntityNotFoundException("Intervenant not found"));
        intervenant.setLogin(intervenantDto.getLogin());
        intervenant.setMdp(intervenantDto.getMdp());
        intervenant.setNom(intervenantDto.getNom());
        intervenant.setPrenom(intervenantDto.getPrenom());
        intervenant.setMail(intervenantDto.getMail());
        intervenant.setDtype(intervenantDto.getDtype());
        intervenant = intervenantRepository.save(intervenant);
        return intervenantEntityToDto(intervenant);
    }

    @Override
    public List<IntervenantDto> getAllIntervenants() {
        List<IntervenantDto> intervenantDtos = new ArrayList<>();
        List<Intervenant> intervenants = intervenantRepository.findAll();
        for (Intervenant intervenant : intervenants) {
            intervenantDtos.add(intervenantEntityToDto(intervenant));
        }
        return intervenantDtos;
    }

    private Intervenant intervenantDtoToEntity(IntervenantDto intervenantDto){
        Intervenant intervenant = new Intervenant();
        intervenant.setUti_id(intervenantDto.getUti_id());
        intervenant.setLogin(intervenantDto.getLogin());
        intervenant.setMdp(intervenantDto.getMdp());
        intervenant.setNom(intervenantDto.getNom());
        intervenant.setPrenom(intervenantDto.getPrenom());
        intervenant.setMail(intervenantDto.getMail());
        intervenant.setDtype(intervenantDto.getDtype());
        intervenant.setCours(new ArrayList<>());
        intervenant.setSeanceFormations(new ArrayList<>());
        return intervenant;
    }

    private IntervenantDto intervenantEntityToDto(Intervenant intervenant){
        IntervenantDto intervenantDto = new IntervenantDto();
        intervenantDto.setUti_id(intervenant.getUti_id());
        intervenantDto.setLogin(intervenant.getLogin());
        intervenantDto.setMdp(intervenant.getMdp());
        intervenantDto.setNom(intervenant.getNom());
        intervenantDto.setPrenom(intervenant.getPrenom());
        intervenantDto.setMail(intervenant.getMail());
        intervenantDto.setDtype(intervenant.getDtype());

        List<CoursDto> coursDtos = new ArrayList<>();
        for(Cours cours : intervenant.getCours()){
            CoursDto coursDto = new CoursDto();
            coursDto.setCou_id(cours.getCou_id());
            coursDto.setIntitule(cours.getIntitule());
            coursDtos.add(coursDto);
        }
        intervenantDto.setCoursDtos(coursDtos);

        List<SeanceFormationDto> seanceFormationDtos = new ArrayList<>();
        for(SeanceFormation seanceFormation : intervenant.getSeanceFormations()){
            SeanceFormationDto seanceFormationDto = new SeanceFormationDto();
            seanceFormationDto.setSea_id(seanceFormation.getSea_id());
            seanceFormationDto.setDureeEffective(seanceFormation.getDureeEffective());
            seanceFormationDto.setValide(seanceFormation.getValide());
            seanceFormationDto.setEstEffectue(seanceFormation.getEstEffectue());
            seanceFormationDto.setCommentaire(seanceFormation.getCommentaire());
            seanceFormationDtos.add(seanceFormationDto);
        }
        intervenantDto.setSeanceFormationDtos(seanceFormationDtos);
        return intervenantDto;
    }
}



