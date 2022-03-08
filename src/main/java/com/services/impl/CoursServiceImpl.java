package com.services.impl;

import com.dtos.CoursDto;
import com.entities.Cours;
import com.repositories.CoursRepository;
import com.services.CoursService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service("coursService")
public class CoursServiceImpl implements CoursService {

    private final CoursRepository coursRepository;

    public CoursServiceImpl(CoursRepository coursRepository){
        this.coursRepository = coursRepository;
    }

    @Override
    public CoursDto addCours(CoursDto coursDto) {
        Cours cours = coursDtoToEntity(coursDto);
        cours = coursRepository.save(cours);
        return coursEntityToDto(cours);
    }

    @Override
    public CoursDto getCoursById(CoursDto coursDto) {
        Cours cours = coursRepository.findById(coursDto.getId()).orElseThrow(() -> new EntityNotFoundException("Cours non trouvé"));
        return coursEntityToDto(cours);
    }

    @Override
    public boolean deleteCours(CoursDto coursDto) {
        coursRepository.deleteById(coursDto.getId());
        return true;
    }

    @Override
    public List<CoursDto> getAllCours() {
        List<CoursDto> coursDtos = new ArrayList<>();
        List<Cours> cours = coursRepository.findAll();
        cours.forEach(unCours -> {
            coursDtos.add(coursEntityToDto(unCours));
        });
        return coursDtos;
    }

    @Override
    public CoursDto editCours(CoursDto coursDto){
        Cours cours = coursRepository.findById(coursDto.getId()).orElseThrow(() -> new EntityNotFoundException("Cours non trouvé"));
        cours.setIntitule(coursDto.getIntitule());
        cours = coursRepository.save(cours);
        return coursEntityToDto(cours);
    }

    private Cours coursDtoToEntity(CoursDto coursDto){
        Cours cours = new Cours();
        cours.setId(coursDto.getId());
        cours.setIntitule(coursDto.getIntitule());
        return cours;
    }

    private CoursDto coursEntityToDto(Cours cours){
        CoursDto coursDto = new CoursDto();
        coursDto.setId(cours.getId());
        coursDto.setIntitule(cours.getIntitule());
        return coursDto;
    }
}



