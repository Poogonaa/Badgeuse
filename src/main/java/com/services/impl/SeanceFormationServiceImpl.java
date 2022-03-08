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
    public SeanceFormationDto getSeanceFormationById(SeanceFormationDto seanceFormationDto) {
        SeanceFormation seanceFormation = seanceFormationRepository.findById(seanceFormationDto.getId()).orElseThrow(()-> new EntityNotFoundException("Seance not found"));
        return seanceFormationEntityToDto(seanceFormation);
    }

    @Override
    public boolean deleteSeanceFormation(SeanceFormationDto seanceFormationDto) {
        seanceFormationRepository.deleteById(seanceFormationDto.getId());
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
        SeanceFormation seanceFormation = seanceFormationRepository.findById(seanceFormationDto.getId()).orElseThrow(() -> new EntityNotFoundException("seance not found"));
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
        seanceFormationDto.setId(seanceFormationDto.getId());
        seanceFormationDto.setDureeEffective(seanceFormationDto.getDureeEffective());
        seanceFormationDto.setValide(seanceFormation.getValide());
        seanceFormationDto.setCommentaire(seanceFormationDto.getCommentaire());
        return seanceFormationDto;
    }

    private SeanceFormation seanceFormationDtoToEntity(SeanceFormationDto seanceFormationDto){
        SeanceFormation seanceFormation = new SeanceFormation();
        seanceFormation.setId(seanceFormation.getId());
        seanceFormation.setDureeEffective(seanceFormation.getDureeEffective());
        seanceFormation.setValide(seanceFormation.getValide());
        seanceFormation.setCommentaire(seanceFormation.getCommentaire());
        return seanceFormation;
    }

}
