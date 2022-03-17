package com.services.impl;

import com.dtos.CoursDto;
import com.dtos.CreneauDto;
import com.dtos.SeanceFormationDto;
import com.entities.*;
import com.repositories.CoursRepository;
import com.repositories.CreneauRepository;
import com.repositories.SeanceFormationRepository;
import com.entities.Creneau;
import com.services.CreneauService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service("creneauService")
public class CreneauServiceImpl implements CreneauService {

    protected final CreneauRepository creneauRepository;
    protected final CoursRepository coursRepository;
    protected final SeanceFormationRepository seanceFormationRepository;

    public CreneauServiceImpl(CreneauRepository creneauRepository, CoursRepository coursRepository, SeanceFormationRepository seanceFormationRepository) {
        this.creneauRepository = creneauRepository;
        this.coursRepository = coursRepository;
        this.seanceFormationRepository = seanceFormationRepository;
    }

    @Override
    public CreneauDto newCreneau(CreneauDto creneauDto) {
        Creneau creneau = creneauDtoToEntity(creneauDto);
        creneau = creneauRepository.save(creneau);
        return creneauEntityToDto(creneau);
    }

    @Override
    public CreneauDto getCreneauById(Long id) {
        Creneau creneau = creneauRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Composante not found"));
        return creneauEntityToDto(creneau);
    }

    @Override
    public boolean deleteCreneau(Long id) {
        creneauRepository.deleteById(id);
        return true;
    }

    @Override
    public List<CreneauDto> getAllCreneaux() {
        List<CreneauDto> creneauDtos = new ArrayList<>();
        List<Creneau> creneaux = creneauRepository.findAll();
        for (Creneau creneau : creneaux) {
            creneauDtos.add(creneauEntityToDto(creneau));
        }
        return creneauDtos;
    }

    @Override
    public CreneauDto editCreneau(CreneauDto creneauDto) {
        Creneau creneau = creneauRepository.findById(creneauDto.getCre_id()).orElseThrow(() -> new EntityNotFoundException("Composante not found"));
        creneau.setDate(creneauDto.getDate());
        creneau.setHeure_debut(creneauDto.getHeure_debut());

        creneau.setDuree(creneauDto.getDuree());
        creneau.setType(creneauDto.getType());
        creneau.setSalle(creneauDto.getSalle());
        return creneauEntityToDto(creneau);
    }

    @Override
    public CreneauDto addCours(CreneauDto creneauDto) {
        Creneau creneau = creneauRepository.findById(creneauDto.getCre_id()).orElseThrow(() -> new EntityNotFoundException("Composante not found"));
        Cours cours = coursRepository.findById(creneauDto.getCoursDto().getCou_id()).orElseThrow(() -> new EntityNotFoundException("Cours not found"));
        creneau.setCours(cours);
        cours.addCreneau(creneau);
        coursRepository.save(cours);
        creneau = creneauRepository.save(creneau);
        return creneauEntityToDto(creneau);
    }

    @Override
    public CreneauDto addSeanceFormation(CreneauDto creneauDto) {
        Creneau creneau = creneauRepository.findById(creneauDto.getCre_id()).orElseThrow(() -> new EntityNotFoundException("Composante not found"));
        SeanceFormation seanceFormation = seanceFormationRepository.findById(creneauDto.getSeanceFormationDtos().get(0).getSea_id()).orElseThrow(() -> new EntityNotFoundException("Filiere_langue not found"));
        creneau.addSeanceFormation(seanceFormation);
        seanceFormation.setCreneau(creneau);
        seanceFormationRepository.save(seanceFormation);
        creneau = creneauRepository.save(creneau);
        return creneauEntityToDto(creneau);
    }

    @Override
    public CreneauDto removeCours(CreneauDto creneauDto) {
        Creneau creneau = creneauRepository.findById(creneauDto.getCre_id()).orElseThrow(() -> new EntityNotFoundException("Composante not found"));
        Cours cours = coursRepository.findById(creneauDto.getCoursDto().getCou_id()).orElseThrow(() -> new EntityNotFoundException("Cours not found"));
        creneau.setCours(null);
        cours.removeCreneau(creneau);
        coursRepository.save(cours);
        creneau = creneauRepository.save(creneau);
        return creneauEntityToDto(creneau);
    }

    @Override
    public CreneauDto removeSeanceFormation(CreneauDto creneauDto) {
        Creneau creneau = creneauRepository.findById(creneauDto.getCre_id()).orElseThrow(() -> new EntityNotFoundException("Composante not found"));
        SeanceFormation seanceFormation = seanceFormationRepository.findById(creneauDto.getSeanceFormationDtos().get(0).getSea_id()).orElseThrow(() -> new EntityNotFoundException("Filiere_langue not found"));
        creneau.removeSeanceFormation(seanceFormation);
        seanceFormation.setCreneau(null);
        seanceFormationRepository.save(seanceFormation);
        creneau = creneauRepository.save(creneau);
        return creneauEntityToDto(creneau);
    }

    private Creneau creneauDtoToEntity(CreneauDto creneauDto){
        Creneau creneau = new Creneau();
        creneau.setCre_id(creneauDto.getCre_id());
        creneau.setDate(creneauDto.getDate());
        creneau.setHeure_debut(creneau.getHeure_debut());
        creneau.setDuree(creneauDto.getDuree());
        creneau.setType(creneauDto.getType());
        creneau.setSalle(creneauDto.getSalle());
        creneau.setCours(null);
        creneau.setSeanceFormations(new ArrayList<>());
        return creneau;
    }

    private CreneauDto creneauEntityToDto(Creneau creneau){
        CreneauDto creneauDto = new CreneauDto();
        creneauDto.setCre_id(creneau.getCre_id());
        creneauDto.setDate(creneau.getDate());
        creneauDto.setHeure_debut(creneau.getHeure_debut());
        creneauDto.setDuree(creneau.getDuree());
        creneauDto.setType(creneau.getType());
        creneauDto.setSalle(creneau.getSalle());

        if(creneau.getCours() != null){
            CoursDto coursDto = new CoursDto();
            coursDto.setCou_id(creneau.getCours().getCou_id());
            coursDto.setIntitule(creneau.getCours().getIntitule());
            creneauDto.setCoursDto(coursDto);
        }

        List<SeanceFormationDto> seanceFormationDtos = new ArrayList<>();
        for(SeanceFormation seanceFormation : creneau.getSeanceFormations()){
            SeanceFormationDto seanceFormationDto = new SeanceFormationDto();
            seanceFormationDto.setSea_id(seanceFormation.getSea_id());
            seanceFormationDto.setDureeEffective(seanceFormation.getDureeEffective());
            seanceFormationDto.setValide(seanceFormation.getValide());
            seanceFormationDto.setEstEffectue(seanceFormation.getEstEffectue());
            seanceFormationDto.setCommentaire(seanceFormation.getCommentaire());
            seanceFormationDtos.add(seanceFormationDto);
        }
        creneauDto.setSeanceFormationDtos(seanceFormationDtos);

        return creneauDto;
    }
}
