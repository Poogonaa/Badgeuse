package com.services.impl;

import com.dtos.SeanceFormationDto;
import com.entities.SeanceFormation;
import com.repositories.SeanceFormationRepository;
import com.services.SeanceFormationService;
import org.springframework.stereotype.Service;

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

    private SeanceFormationDto seanceFormationEntityToDto(SeanceFormation seanceFormation){
        SeanceFormationDto seanceFormationDto = new SeanceFormationDto();
        seanceFormationDto.setSea_id(seanceFormation.getSea_id());
        seanceFormationDto.setDureeEffective(seanceFormation.getDureeEffective());
        seanceFormationDto.setValide(seanceFormation.getValide());
        seanceFormationDto.setCommentaire(seanceFormation.getCommentaire());
        return seanceFormationDto;
    }

    private SeanceFormation seanceFormationDtoToEntity(SeanceFormationDto seanceFormationDto){
        SeanceFormation seanceFormation = new SeanceFormation();
        seanceFormation.setSea_id(seanceFormationDto.getSea_id());
        seanceFormation.setDureeEffective(seanceFormationDto.getDureeEffective());
        seanceFormation.setValide(seanceFormationDto.getValide());
        seanceFormation.setCommentaire(seanceFormationDto.getCommentaire());
        return seanceFormation;
    }

}
