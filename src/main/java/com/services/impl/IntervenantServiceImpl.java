package com.services.impl;

import com.dtos.IntervenantDto;
import com.entities.Intervenant;
import com.repositories.IntervenantRepository;
import com.services.IntervenantService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service("intervenantService")
public class IntervenantServiceImpl implements IntervenantService {

    private final IntervenantRepository intervenantRepository;

    public IntervenantServiceImpl(IntervenantRepository intervenantRepository){
        this.intervenantRepository = intervenantRepository;
    }

    @Override
    public IntervenantDto addIntervenant(IntervenantDto intervenantDto) {
        Intervenant intervenant = intervenantDtoToEntity(intervenantDto);
        intervenant = intervenantRepository.save(intervenant);
        return intervenantEntityToDto(intervenant);
    }

    @Override
    public IntervenantDto getIntervenantById(Long intervenantId) {
        Intervenant intervenant = intervenantRepository.findById(intervenantId).orElseThrow(() -> new EntityNotFoundException("Intervenant not found"));
        return intervenantEntityToDto(intervenant);
    }

    @Override
    public boolean deleteIntervenant(Long intervenantId) {
        intervenantRepository.deleteById(intervenantId);
        return true;
    }

    @Override
    public List<IntervenantDto> getAllIntervenants() {
        List<IntervenantDto> intervenantDtos = new ArrayList<>();
        List<Intervenant> intervenants = intervenantRepository.findAll();
        intervenants.forEach(intervenant -> {
            intervenantDtos.add(intervenantEntityToDto(intervenant));
        });
        return intervenantDtos;
    }

    private Intervenant intervenantDtoToEntity(IntervenantDto intervenantDto){
        Intervenant intervenant = new Intervenant();
        intervenant.setId(intervenantDto.getId());
        intervenant.setLogin(intervenantDto.getLogin());
        intervenant.setMdp(intervenantDto.getMdp());
        intervenant.setNom(intervenantDto.getNom());
        intervenant.setPrenom(intervenantDto.getPrenom());
        intervenant.setMail(intervenantDto.getMail());
        return intervenant;
    }

    private IntervenantDto intervenantEntityToDto(Intervenant intervenant){
        IntervenantDto intervenantDto = new IntervenantDto();
        intervenantDto.setId(intervenant.getId());
        intervenantDto.setLogin(intervenant.getLogin());
        intervenantDto.setMdp(intervenant.getMdp());
        intervenantDto.setNom(intervenant.getNom());
        intervenantDto.setPrenom(intervenant.getPrenom());
        intervenantDto.setMail(intervenant.getMail());
        return intervenantDto;
    }
}



