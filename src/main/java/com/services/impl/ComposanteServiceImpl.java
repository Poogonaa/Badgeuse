package com.services.impl;

import com.dtos.ComposanteDto;
import com.entities.Composante;
import com.repositories.ComposanteRepository;
import com.services.ComposanteService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service("composanteService")
public class ComposanteServiceImpl implements ComposanteService {
    protected final ComposanteRepository composanteRepository;

    public ComposanteServiceImpl(ComposanteRepository composanteRepository) {
        this.composanteRepository = composanteRepository;
    }

    @Override
    public ComposanteDto getComposanteById(ComposanteDto composanteDto) {
        Composante composante = composanteRepository.findById(composanteDto.getCom_id()).orElseThrow(() -> new EntityNotFoundException("Composante not found"));
        return composanteEntityToDto(composante);
    }

    @Override
    public boolean deleteComposante(ComposanteDto composanteDto) {
        composanteRepository.deleteById(composanteDto.getCom_id());
        return true;
    }

    @Override
    public List<ComposanteDto> getAllComposantes() {
        List<ComposanteDto> composanteDtos = new ArrayList<>();
        List<Composante> composantes = composanteRepository.findAll();
        composantes.forEach(composante -> {
            composanteDtos.add(composanteEntityToDto(composante));
        });
        return composanteDtos;
    }

    @Override
    public ComposanteDto editComposante(ComposanteDto composanteDto) {
        Composante composante = composanteRepository.findById(composanteDto.getCom_id()).orElseThrow(() -> new EntityNotFoundException("Composante not found"));
        composante.setNomComposante(composanteDto.getNomComposante());
        composante = composanteRepository.save(composante);
        return composanteEntityToDto(composante);
    }

    @Override
    public ComposanteDto addComposante(ComposanteDto composanteDto) {
        Composante composante = composanteDtoToEntity(composanteDto);
        composante = composanteRepository.save(composante);
        return composanteEntityToDto(composante);
    }

    private Composante composanteDtoToEntity(ComposanteDto composanteDto){
        Composante composante = new Composante();
        composante.setCom_id(composanteDto.getCom_id());
        composante.setNomComposante(composanteDto.getNomComposante());
        return composante;
    }

    private ComposanteDto composanteEntityToDto(Composante composante){
        ComposanteDto composanteDto = new ComposanteDto();
        composanteDto.setCom_id(composante.getCom_id());
        composanteDto.setNomComposante(composante.getNomComposante());
        return composanteDto;
    }


}
