package com.services.impl;

import com.dtos.ComposanteDto;
import com.dtos.ResponsableDto;
import com.entities.*;
import com.repositories.ComposanteRepository;
import com.repositories.ResponsableRepository;
import com.services.ResponsableService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service("responsableService")
public class ResponsableServiceImpl implements ResponsableService {
    protected final ResponsableRepository responsableRepository;
    protected final ComposanteRepository composanteRepository;

    public ResponsableServiceImpl(ResponsableRepository responsableRepository, ComposanteRepository composanteRepository) {
        this.responsableRepository = responsableRepository;
        this.composanteRepository = composanteRepository;
    }

    @Override
    public ResponsableDto newResponsable(ResponsableDto responsableDto) {
        Responsable responsable = responsableDtoToEntity(responsableDto);
        responsable = responsableRepository.save(responsable);
        return responsableEntityToDto(responsable);

    }

    @Override
    public ResponsableDto getResponsableById(Long id) {
        Responsable responsable = responsableRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Gestionnaire not found"));
        return responsableEntityToDto(responsable);
    }

    @Override
    public boolean deleteResponsable(Long id) {
        responsableRepository.deleteById(id);
        return true;
    }

    @Override
    public List<ResponsableDto> getAllResponsables() {
        List<ResponsableDto> responsableDtos = new ArrayList<>();
        List<Responsable> responsables = responsableRepository.findAll();
        for (Responsable responsable : responsables) {
            responsableDtos.add(responsableEntityToDto(responsable));
        }
        return responsableDtos;
    }

    @Override
    public ResponsableDto editResponsable(ResponsableDto responsableDto) {
        Responsable responsable = responsableRepository.findById(responsableDto.getUti_id()).orElseThrow(() -> new EntityNotFoundException("Utilisateur not found"));
        responsable.setLogin(responsableDto.getLogin());
        responsable.setMdp(responsableDto.getMdp());
        responsable.setNom(responsableDto.getNom());
        responsable.setPrenom(responsableDto.getPrenom());
        responsable.setMail(responsableDto.getMail());
        responsable.setDtype(responsableDto.getDtype());
        responsable = responsableRepository.save(responsable);
        return responsableEntityToDto(responsable);
    }

    @Override
    public ResponsableDto addComposante(ResponsableDto responsableDto) {
        Responsable responsable = responsableRepository.findById(responsableDto.getUti_id()).orElseThrow(() -> new EntityNotFoundException("Composante not found"));
        Composante composante = composanteRepository.findById(responsableDto.getComposanteDto().getCom_id()).orElseThrow(() -> new EntityNotFoundException("Composante not found"));
        responsable.setComposante(composante);
        composante.addResponsable(responsable);
        composanteRepository.save(composante);
        responsable = responsableRepository.save(responsable);
        return responsableEntityToDto(responsable);
    }

    @Override
    public ResponsableDto removeComposante(ResponsableDto responsableDto) {
        Responsable responsable = responsableRepository.findById(responsableDto.getUti_id()).orElseThrow(() -> new EntityNotFoundException("Composante not found"));
        Composante composante = composanteRepository.findById(responsableDto.getComposanteDto().getCom_id()).orElseThrow(() -> new EntityNotFoundException("Composante not found"));
        responsable.setComposante(null);
        composante.removeResponsable(responsable);
        composanteRepository.save(composante);
        responsable = responsableRepository.save(responsable);
        return responsableEntityToDto(responsable);
    }

    private ResponsableDto responsableEntityToDto(Responsable responsable){
        ResponsableDto responsableDto = new ResponsableDto();
        responsableDto.setUti_id(responsable.getUti_id());
        responsableDto.setLogin(responsable.getLogin());
        responsableDto.setMdp(responsable.getMdp());
        responsableDto.setNom(responsable.getNom());
        responsableDto.setPrenom(responsable.getPrenom());
        responsableDto.setMail(responsable.getMail());
        responsableDto.setDtype(responsable.getDtype());

        if(responsable.getComposante() != null){
            ComposanteDto composanteDto = new ComposanteDto();
            composanteDto.setCom_id(responsable.getComposante().getCom_id());
            composanteDto.setNomComposante(responsable.getComposante().getNomComposante());
            responsableDto.setComposanteDto(composanteDto);
        }

        return responsableDto;
    }

    private Responsable responsableDtoToEntity(ResponsableDto responsableDto){
        Responsable responsable = new Responsable();
        responsable.setUti_id(responsableDto.getUti_id());
        responsable.setLogin(responsableDto.getLogin());
        responsable.setMdp(responsableDto.getMdp());
        responsable.setNom(responsableDto.getNom());
        responsable.setPrenom(responsableDto.getPrenom());
        responsable.setMail(responsableDto.getMail());
        responsable.setDtype(responsableDto.getDtype());
        responsable.setComposante(null);
        return responsable;
    }
}
