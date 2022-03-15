package com.services.impl;

import com.dtos.SeanceFormationDto;
import com.entities.SeanceFormation;
import com.entities.Utilisateur;
import com.repositories.IntervenantRepository;
import com.repositories.SeanceFormationRepository;
import com.services.SeanceFormationService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service("seanceFormationService")
public class SeanceFormationServiceImpl implements SeanceFormationService {

    protected final SeanceFormationRepository seanceFormationRepository;

    public SeanceFormationServiceImpl(SeanceFormationRepository seanceFormationRepository) {
        this.seanceFormationRepository = seanceFormationRepository;
    }

    @Override
    public SeanceFormationDto getSeanceFormationById(Long id) {
        SeanceFormation seanceFormation = seanceFormationRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Seance not found"));
        return seanceFormationEntityToDto(seanceFormation);
    }

    @Override
    public boolean deleteSeanceFormation(Long id) {
        seanceFormationRepository.deleteById(id);
        return true;
    }

    @Override
    public List<SeanceFormationDto> getAllSeancesFormations() {
        List<SeanceFormationDto> seanceFormationDtos = new ArrayList<>();
        List<SeanceFormation> seanceFormations = seanceFormationRepository.findAll();
        seanceFormations.forEach(seanceFormation -> {
            seanceFormationDtos.add(seanceFormationEntityToDto(seanceFormation));
        });
        return seanceFormationDtos;
    }


    @Override
    public List<SeanceFormationDto> getAllSeancesFormationsByIntervenant(Long id){
        List<SeanceFormationDto> seanceFormationDtos = new ArrayList<>();
        List<SeanceFormation> seanceFormations = seanceFormationRepository.findAll();
        //on enlève ceux qui n'ont pas pour Intervenant celui que l'on veut
        for(SeanceFormation seance : seanceFormations){
            if(seance.getIntervenant().getUti_id() == id){
                seanceFormationDtos.add(seanceFormationEntityToDto(seance));
            }
        }
        return seanceFormationDtos;
    }

    @Override
    public List<SeanceFormationDto> getAllSeancesFormationsEffectueesByIntervenant(Long id){
        List<SeanceFormationDto> seanceFormationDtos = new ArrayList<>();
        List<SeanceFormation> seanceFormations = seanceFormationRepository.findAll();
        //on enlève ceux qui n'ont pas pour Intervenant ciblé ou qui ne sont pas effectuées.
        for(int i = 0 ; i < seanceFormations.size() ; i++){
            SeanceFormation seance = seanceFormations.get(i);
            if(seance.getIntervenant().getUti_id() == id && seance.getEstEffectue())
                seanceFormationDtos.add(seanceFormationEntityToDto(seance));
            i++;
        }
        return seanceFormationDtos;
    }

    @Override
    public SeanceFormationDto editSeanceFormation(SeanceFormationDto seanceFormationDto) {
        SeanceFormation seanceFormation = seanceFormationRepository.findById(seanceFormationDto.getSea_id()).orElseThrow(() -> new EntityNotFoundException("seance not found"));
        seanceFormation.setEstEffectue(seanceFormationDto.getEstEffectue());
        seanceFormation.setValide(seanceFormationDto.getValide());
        seanceFormation.setCommentaire(seanceFormationDto.getCommentaire());



        seanceFormation = seanceFormationRepository.save(seanceFormation);
        return seanceFormationEntityToDto(seanceFormation);
    }

    @Override
    public SeanceFormationDto addSeanceFormation(SeanceFormationDto seanceFormationDto) {
        SeanceFormation seanceFormation = seanceFormationDtoToEntity(seanceFormationDto);
        seanceFormation = seanceFormationRepository.save(seanceFormation);
        return seanceFormationEntityToDto(seanceFormation);
    }

    @Override
    public SeanceFormationDto valider(SeanceFormationDto seanceFormationDto){
        SeanceFormation seance = seanceFormationRepository.findById(seanceFormationDto.getSea_id()).orElseThrow(() -> new EntityNotFoundException("SeanceFormation not found"));
        seance.setValide(seanceFormationDto.getValide());
        seance = seanceFormationRepository.save(seance);
        return seanceFormationEntityToDto(seance);
    }

    private SeanceFormationDto seanceFormationEntityToDto(SeanceFormation seanceFormation){
        SeanceFormationDto seanceFormationDto = new SeanceFormationDto();
        seanceFormationDto.setSea_id(seanceFormation.getSea_id());
        seanceFormationDto.setDureeEffective(seanceFormation.getDureeEffective());
        seanceFormationDto.setValide(seanceFormation.getValide());
        seanceFormationDto.setEstEffectue(seanceFormation.getEstEffectue());
        seanceFormationDto.setCommentaire(seanceFormation.getCommentaire());
        return seanceFormationDto;
    }

    private SeanceFormation seanceFormationDtoToEntity(SeanceFormationDto seanceFormationDto){
        SeanceFormation seanceFormation = new SeanceFormation();
        seanceFormation.setSea_id(seanceFormationDto.getSea_id());
        seanceFormation.setDureeEffective(seanceFormationDto.getDureeEffective());
        seanceFormation.setValide(seanceFormationDto.getValide());
        seanceFormation.setEstEffectue(seanceFormationDto.getEstEffectue());
        seanceFormation.setCommentaire(seanceFormationDto.getCommentaire());
        return seanceFormation;
    }

}
