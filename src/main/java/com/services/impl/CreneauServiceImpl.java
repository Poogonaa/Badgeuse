package com.services.impl;

import com.dtos.CreneauDto;
import com.entities.Creneau;
import com.repositories.CreaneauRepository;
import com.services.CreneauService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service("creneauService")
public class CreneauServiceImpl implements CreneauService {
    protected final CreaneauRepository creaneauRepository;

    public CreneauServiceImpl(CreaneauRepository creaneauRepository) {
        this.creaneauRepository = creaneauRepository;
    }

    @Override
    public CreneauDto addCreneau(CreneauDto creneauDto) {
        Creneau creneau = creneauDtoToEntity(creneauDto);
        creneau = creaneauRepository.save(creneau);
        return creneauEntityToDto(creneau);
    }

    @Override
    public CreneauDto getCreneauById(Long id) {
        Creneau creneau = creaneauRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Composante not found"));
        return creneauEntityToDto(creneau);
    }

    @Override
    public boolean deleteCreneau(Long id) {
        creaneauRepository.deleteById(id);
        return true;
    }

    @Override
    public List<CreneauDto> getAllCreneaux() {
        List<CreneauDto> creneauDtos = new ArrayList<>();
        List<Creneau> creneaux = creaneauRepository.findAll();
        creneaux.forEach(creneau -> {
            creneauDtos.add(creneauEntityToDto(creneau));
        });
        return creneauDtos;
    }

    @Override
    public CreneauDto editCreneau(CreneauDto creneauDto) {
        Creneau creneau = creaneauRepository.findById(creneauDto.getCre_id()).orElseThrow(() -> new EntityNotFoundException("Composante not found"));
        creneau.setDate_heure(creneauDto.getDate_heure());
        creneau.setDuree(creneauDto.getDuree());
        creneau.setType(creneauDto.getType());
        creneau.setSalle(creneauDto.getSalle());
        return creneauEntityToDto(creneau);
    }

    private Creneau creneauDtoToEntity(CreneauDto creneauDto){
        Creneau creneau = new Creneau();
        creneau.setCre_id(creneauDto.getCre_id());
        creneau.setDate_heure(creneauDto.getDate_heure());
        creneau.setDuree(creneauDto.getDuree());
        creneau.setType(creneauDto.getType());
        creneau.setSalle(creneauDto.getSalle());
        return creneau;
    }

    private CreneauDto creneauEntityToDto(Creneau creneau){
        CreneauDto creneauDto = new CreneauDto();
        creneauDto.setCre_id(creneau.getCre_id());
        creneauDto.setDate_heure(creneau.getDate_heure());
        creneauDto.setDuree(creneau.getDuree());
        creneauDto.setType(creneau.getType());
        creneauDto.setSalle(creneau.getSalle());
        return creneauDto;
    }
}
