package com.services.impl;

import com.dtos.*;
import com.entities.*;
import com.repositories.CoursRepository;
import com.repositories.CreneauRepository;
import com.repositories.Filiere_langueRepository;
import com.repositories.IntervenantRepository;
import com.services.CoursService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service("coursService")
public class CoursServiceImpl implements CoursService {

    private final CoursRepository coursRepository;
    private  final IntervenantRepository intervenantRepository;
    private final Filiere_langueRepository filiere_langueRepository;
    private final CreneauRepository creneauRepository;

    public CoursServiceImpl(CoursRepository coursRepository, IntervenantRepository intervenantRepository, Filiere_langueRepository filiere_langueRepository, CreneauRepository creneauRepository){
        this.coursRepository = coursRepository;
        this.intervenantRepository = intervenantRepository;
        this.filiere_langueRepository = filiere_langueRepository;
        this.creneauRepository = creneauRepository;
    }

    @Override
    public CoursDto newCours(CoursDto coursDto) {
        Cours cours = coursDtoToEntity(coursDto);
        cours = coursRepository.save(cours);
        return coursEntityToDto(cours);
    }

    @Override
    public CoursDto getCoursById(Long id) {
        Cours cours = coursRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cours non trouvé"));
        return coursEntityToDto(cours);
    }

    @Override
    public boolean deleteCours(Long id) {
        coursRepository.deleteById(id);
        return true;
    }

    @Override
    public List<CoursDto> getAllCours() {
        List<CoursDto> coursDtos = new ArrayList<>();
        List<Cours> cours = coursRepository.findAll();
        for (Cours unCours : cours) {
            coursDtos.add(coursEntityToDto(unCours));
        }
        return coursDtos;
    }

    @Override
    public CoursDto editCours(CoursDto coursDto){
        Cours cours = coursRepository.findById(coursDto.getCou_id()).orElseThrow(() -> new EntityNotFoundException("Cours non trouvé"));
        cours.setIntitule(coursDto.getIntitule());
        cours = coursRepository.save(cours);
        return coursEntityToDto(cours);
    }

    @Override
    public CoursDto addIntervenant(CoursDto coursDto) {
        Cours cours = coursRepository.findById(coursDto.getCou_id()).orElseThrow(() -> new EntityNotFoundException("Cours not found"));
        Intervenant intervenant = intervenantRepository.findById(coursDto.getIntervenantDtos().get(0).getUti_id()).orElseThrow(() -> new EntityNotFoundException("Intervenant not found"));
        cours.addIntervenant(intervenant);
        intervenant.addCours(cours);
        intervenantRepository.save(intervenant);
        cours = coursRepository.save(cours);
        return coursEntityToDto(cours);
    }

    @Override
    public CoursDto addFiliere_langue(CoursDto coursDto) {
        Cours cours = coursRepository.findById(coursDto.getCou_id()).orElseThrow(() -> new EntityNotFoundException("Cours not found"));
        Filiere_langue filiere_langue = filiere_langueRepository.findById(coursDto.getFiliere_langueDtos().get(0).getFil_id()).orElseThrow(() -> new EntityNotFoundException("Filiere_langue not found"));
        cours.addFiliere_langue(filiere_langue);
        filiere_langue.addCours(cours);
        filiere_langueRepository.save(filiere_langue);
        cours = coursRepository.save(cours);
        return coursEntityToDto(cours);
    }

    @Override
    public CoursDto addCreneau(CoursDto coursDto) {
        Cours cours = coursRepository.findById(coursDto.getCou_id()).orElseThrow(() -> new EntityNotFoundException("Cours not found"));
        Creneau creneau = creneauRepository.findById(coursDto.getCreneauDtos().get(0).getCre_id()).orElseThrow(() -> new EntityNotFoundException("Creneau not found"));
        cours.addCreneau(creneau);
        creneau.setCours(cours);
        creneauRepository.save(creneau);
        cours = coursRepository.save(cours);
        return coursEntityToDto(cours);
    }

    @Override
    public CoursDto removeIntervenant(CoursDto coursDto) {
        Cours cours = coursRepository.findById(coursDto.getCou_id()).orElseThrow(() -> new EntityNotFoundException("Cours not found"));
        Intervenant intervenant = intervenantRepository.findById(coursDto.getIntervenantDtos().get(0).getUti_id()).orElseThrow(() -> new EntityNotFoundException("Intervenant not found"));
        cours.removeIntervenant(intervenant);
        intervenant.removeCours(cours);
        intervenantRepository.save(intervenant);
        cours = coursRepository.save(cours);
        return coursEntityToDto(cours);
    }

    @Override
    public CoursDto removeFiliere_langue(CoursDto coursDto) {
        Cours cours = coursRepository.findById(coursDto.getCou_id()).orElseThrow(() -> new EntityNotFoundException("Cours not found"));
        Filiere_langue filiere_langue = filiere_langueRepository.findById(coursDto.getFiliere_langueDtos().get(0).getFil_id()).orElseThrow(() -> new EntityNotFoundException("Filiere_langue not found"));
        cours.removeFiliere_langue(filiere_langue);
        filiere_langue.removeCours(cours);
        filiere_langueRepository.save(filiere_langue);
        cours = coursRepository.save(cours);
        return coursEntityToDto(cours);
    }

    @Override
    public CoursDto removeCreneau(CoursDto coursDto) {
        Cours cours = coursRepository.findById(coursDto.getCou_id()).orElseThrow(() -> new EntityNotFoundException("Cours not found"));
        Creneau creneau = creneauRepository.findById(coursDto.getCreneauDtos().get(0).getCre_id()).orElseThrow(() -> new EntityNotFoundException("Creneau not found"));
        cours.removeCreneau(creneau);
        creneau.setCours(null);
        creneauRepository.save(creneau);
        cours = coursRepository.save(cours);
        return coursEntityToDto(cours);
    }

    private Cours coursDtoToEntity(CoursDto coursDto){
        Cours cours = new Cours();
        cours.setCou_id(coursDto.getCou_id());
        cours.setIntitule(coursDto.getIntitule());
        cours.setCreneaux(new ArrayList<>());
        cours.setFiliere_langues(new ArrayList<>());
        cours.setIntervenants(new ArrayList<>());
        return cours;
    }

    private CoursDto coursEntityToDto(Cours cours){
        CoursDto coursDto = new CoursDto();
        coursDto.setCou_id(cours.getCou_id());
        coursDto.setIntitule(cours.getIntitule());

        List<IntervenantDto> intervenantDtos = new ArrayList<>();
        for(Intervenant intervenant : cours.getIntervenants()){
            IntervenantDto utilisateurDto = new IntervenantDto();
            utilisateurDto.setUti_id(intervenant.getUti_id());
            utilisateurDto.setLogin(intervenant.getLogin());
            utilisateurDto.setMdp(intervenant.getMdp());
            utilisateurDto.setNom(intervenant.getNom());
            utilisateurDto.setPrenom(intervenant.getPrenom());
            utilisateurDto.setMail(intervenant.getMail());
            intervenantDtos.add(utilisateurDto);
        }
        coursDto.setIntervenantDtos(intervenantDtos);

        List<Filiere_langueDto> filiere_langueDtos = new ArrayList<>();
        for(Filiere_langue filiere_langue : cours.getFiliere_langues()){
            Filiere_langueDto filiere_langueDto = new Filiere_langueDto();
            filiere_langueDto.setFil_id(filiere_langue.getFil_id());
            filiere_langueDto.setCode(filiere_langue.getCode());
            filiere_langueDto.setNom(filiere_langue.getNom());
            filiere_langueDtos.add(filiere_langueDto);
        }
        coursDto.setFiliere_langueDtos(filiere_langueDtos);

        List<CreneauDto> creneauDtos = new ArrayList<>();
        for(Creneau creneau : cours.getCreneaux()){
            CreneauDto creneauDto = new CreneauDto();
            creneauDto.setCre_id(creneau.getCre_id());
            creneauDto.setDate_heure(creneau.getDate_heure());
            creneauDto.setDuree(creneau.getDuree());
            creneauDto.setType(creneau.getType());
            creneauDto.setSalle(creneau.getSalle());
            creneauDtos.add(creneauDto);
        }
        coursDto.setCreneauDtos(creneauDtos);
        return coursDto;
    }
}



