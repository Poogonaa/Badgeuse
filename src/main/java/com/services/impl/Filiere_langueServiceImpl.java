package com.services.impl;

import com.dtos.Filiere_langueDto;
import com.entities.Filiere_langue;
import com.repositories.Filiere_langueRepository;
import com.services.Filiere_langueService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service("filiere_langueService")
public class Filiere_langueServiceImpl implements Filiere_langueService {

    protected final Filiere_langueRepository filiere_langueRepository;

    public Filiere_langueServiceImpl(Filiere_langueRepository filiere_langueRepository) {
        this.filiere_langueRepository = filiere_langueRepository;
    }

    @Override
    public Filiere_langueDto addFiliere_langue(Filiere_langueDto filiere_langueDto) {
        Filiere_langue filiere_langue = filiere_langueDtoToEntity(filiere_langueDto);
        filiere_langue = filiere_langueRepository.save(filiere_langue);
        return filiere_langueEntityToDto(filiere_langue);
    }

    @Override
    public Filiere_langueDto getFiliere_langueById(Long id) {
        Filiere_langue filiere_langue = filiere_langueRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Utilisateur not found"));
        return filiere_langueEntityToDto(filiere_langue);
    }

    @Override
    public boolean deleteFiliere_langue(Long id) {
        filiere_langueRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Filiere_langueDto> getAllFiliere_langues() {
        List<Filiere_langueDto> filiere_langueDtos = new ArrayList<>();
        List<Filiere_langue> filiere_langues = filiere_langueRepository.findAll();
        filiere_langues.forEach(filiere_langue -> {
            filiere_langueDtos.add(filiere_langueEntityToDto(filiere_langue));
        });
        return filiere_langueDtos;
    }

    @Override
    public Filiere_langueDto editFiliere_langue(Filiere_langueDto filiere_langueDto) {
        Filiere_langue filiere_langue = filiere_langueRepository.findById(filiere_langueDto.getFil_id()).orElseThrow(() -> new EntityNotFoundException("Filiere_langue not found"));
        filiere_langue.setCode(filiere_langueDto.getCode());
        filiere_langue.setNom(filiere_langueDto.getNom());
        filiere_langue = filiere_langueRepository.save(filiere_langue);
        return filiere_langueEntityToDto(filiere_langue);
    }

    private Filiere_langue filiere_langueDtoToEntity(Filiere_langueDto filiere_langueDto){
        Filiere_langue filiere_langue = new Filiere_langue();
        filiere_langue.setFil_id(filiere_langueDto.getFil_id());
        filiere_langue.setCode(filiere_langueDto.getCode());
        filiere_langue.setNom(filiere_langueDto.getNom());
        return filiere_langue;
    }

    private Filiere_langueDto filiere_langueEntityToDto(Filiere_langue filiere_langue){
        Filiere_langueDto filiere_langueDto = new Filiere_langueDto();
        filiere_langueDto.setFil_id(filiere_langue.getFil_id());
        filiere_langueDto.setCode(filiere_langue.getCode());
        filiere_langueDto.setNom(filiere_langue.getNom());
        return filiere_langueDto;
    }
}
