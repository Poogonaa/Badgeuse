package com.services.impl;

import com.dtos.ComposanteDto;
import com.dtos.CoursDto;
import com.dtos.Filiere_langueDto;
import com.entities.Composante;
import com.entities.Cours;
import com.entities.Filiere_langue;
import com.repositories.ComposanteRepository;
import com.repositories.CoursRepository;
import com.repositories.Filiere_langueRepository;
import com.services.Filiere_langueService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service("filiere_langueService")
public class Filiere_langueServiceImpl implements Filiere_langueService {

    protected final Filiere_langueRepository filiere_langueRepository;
    protected final ComposanteRepository composanteRepository;
    protected final CoursRepository coursRepository;

    public Filiere_langueServiceImpl(Filiere_langueRepository filiere_langueRepository, ComposanteRepository composanteRepository, CoursRepository coursRepository) {
        this.filiere_langueRepository = filiere_langueRepository;
        this.composanteRepository = composanteRepository;
        this.coursRepository = coursRepository;
    }

    @Override
    public Filiere_langueDto newFiliere_langue(Filiere_langueDto filiere_langueDto) {
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
        for (Filiere_langue filiere_langue : filiere_langues) {
            filiere_langueDtos.add(filiere_langueEntityToDto(filiere_langue));
        }
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

    @Override
    public Filiere_langueDto addComposante(Filiere_langueDto filiere_langueDto) {
        Filiere_langue filiere_langue = filiere_langueRepository.findById(filiere_langueDto.getFil_id()).orElseThrow(() -> new EntityNotFoundException("Filiere_langue not found"));
        Composante composante = composanteRepository.findById(filiere_langueDto.getComposanteDto().getCom_id()).orElseThrow(() -> new EntityNotFoundException("Composante not found"));
        filiere_langue.setComposante(composante);
        composante.addFiliere_langue(filiere_langue);
        composanteRepository.save(composante);
        filiere_langue = filiere_langueRepository.save(filiere_langue);
        return filiere_langueEntityToDto(filiere_langue);
    }

    @Override
    public Filiere_langueDto addCours(Filiere_langueDto filiere_langueDto) {
        Filiere_langue filiere_langue = filiere_langueRepository.findById(filiere_langueDto.getFil_id()).orElseThrow(() -> new EntityNotFoundException("Filiere_langue not found"));
        Cours cours = coursRepository.findById(filiere_langueDto.getCoursDtos().get(0).getCou_id()).orElseThrow(() -> new EntityNotFoundException("Cours not found"));
        filiere_langue.addCours(cours);
        filiere_langue = filiere_langueRepository.save(filiere_langue);
        return filiere_langueEntityToDto(filiere_langue);
    }

    @Override
    public Filiere_langueDto removeComposante(Filiere_langueDto filiere_langueDto) {
        Filiere_langue filiere_langue = filiere_langueRepository.findById(filiere_langueDto.getFil_id()).orElseThrow(() -> new EntityNotFoundException("Filiere_langue not found"));
        Composante composante = composanteRepository.findById(filiere_langueDto.getComposanteDto().getCom_id()).orElseThrow(() -> new EntityNotFoundException("Composante not found"));
        filiere_langue.setComposante(null);
        composante.removeFiliere_langue(filiere_langue);
        composanteRepository.save(composante);
        filiere_langue = filiere_langueRepository.save(filiere_langue);
        return filiere_langueEntityToDto(filiere_langue);
    }

    @Override
    public Filiere_langueDto removeCours(Filiere_langueDto filiere_langueDto) {
        Filiere_langue filiere_langue = filiere_langueRepository.findById(filiere_langueDto.getFil_id()).orElseThrow(() -> new EntityNotFoundException("Filiere_langue not found"));
        Cours cours = coursRepository.findById(filiere_langueDto.getCoursDtos().get(0).getCou_id()).orElseThrow(() -> new EntityNotFoundException("Cours not found"));
        filiere_langue.removeCours(cours);
        filiere_langue = filiere_langueRepository.save(filiere_langue);
        return filiere_langueEntityToDto(filiere_langue);
    }

    private Filiere_langue filiere_langueDtoToEntity(Filiere_langueDto filiere_langueDto){
        Filiere_langue filiere_langue = new Filiere_langue();
        filiere_langue.setFil_id(filiere_langueDto.getFil_id());
        filiere_langue.setCode(filiere_langueDto.getCode());
        filiere_langue.setNom(filiere_langueDto.getNom());
        filiere_langue.setComposante(null);
        filiere_langue.setCours(new ArrayList<>());
        return filiere_langue;
    }

    private Filiere_langueDto filiere_langueEntityToDto(Filiere_langue filiere_langue){
        Filiere_langueDto filiere_langueDto = new Filiere_langueDto();
        filiere_langueDto.setFil_id(filiere_langue.getFil_id());
        filiere_langueDto.setCode(filiere_langue.getCode());
        filiere_langueDto.setNom(filiere_langue.getNom());

        if(filiere_langue.getComposante() != null){
            ComposanteDto composanteDto = new ComposanteDto();
            composanteDto.setCom_id(filiere_langue.getComposante().getCom_id());
            composanteDto.setNomComposante(filiere_langue.getComposante().getNomComposante());
            filiere_langueDto.setComposanteDto(composanteDto);
        }

        List<CoursDto> coursDtos = new ArrayList<>();
        for(Cours cours : filiere_langue.getCours()){
            CoursDto coursDto = new CoursDto();
            coursDto.setCou_id(cours.getCou_id());
            coursDto.setIntitule(cours.getIntitule());
            coursDtos.add(coursDto);
        }
        filiere_langueDto.setCoursDtos(coursDtos);
        return filiere_langueDto;
    }
}
