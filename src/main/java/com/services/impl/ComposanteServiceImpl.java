package com.services.impl;

import com.dtos.ComposanteDto;
import com.dtos.Filiere_langueDto;
import com.dtos.ResponsableDto;
import com.entities.Composante;
import com.entities.Filiere_langue;
import com.entities.Responsable;
import com.repositories.ComposanteRepository;
import com.repositories.Filiere_langueRepository;
import com.repositories.ResponsableRepository;
import com.repositories.UtilisateurRepository;
import com.services.ComposanteService;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service("composanteService")
public class ComposanteServiceImpl implements ComposanteService {
    protected final ComposanteRepository composanteRepository;
    protected final Filiere_langueRepository filiere_langueRepository;
    protected final ResponsableRepository responsableRepository;

    public ComposanteServiceImpl(ComposanteRepository composanteRepository, Filiere_langueRepository filiere_langueRepository, ResponsableRepository responsableRepository) {
        this.composanteRepository = composanteRepository;
        this.filiere_langueRepository = filiere_langueRepository;
        this.responsableRepository = responsableRepository;
    }

    @Override
    public ComposanteDto getComposanteById(Long id) {
        Composante composante = composanteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Composante not found"));
        return composanteEntityToDto(composante);
    }

    @Override
    public boolean deleteComposante(Long id) {
        composanteRepository.deleteById(id);
        return true;
    }

    @Override
    public List<ComposanteDto> getAllComposantes() {
        List<ComposanteDto> composanteDtos = new ArrayList<>();
        List<Composante> composantes = composanteRepository.findAll();
        for (Composante composante : composantes) {
            composanteDtos.add(composanteEntityToDto(composante));
        }
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
    public ComposanteDto newComposante(ComposanteDto composanteDto) {
        Composante composante = composanteDtoToEntity(composanteDto);
        composante = composanteRepository.save(composante);
        return composanteEntityToDto(composante);
    }

    @Override
    public ComposanteDto addFiliere_langue(ComposanteDto composanteDto) {
        Composante composante = composanteRepository.findById(composanteDto.getCom_id()).orElseThrow(() -> new EntityNotFoundException("Composante not found"));
        Filiere_langue filiere_langue = filiere_langueRepository.findById(composanteDto.getFiliere_langueDtos().get(0).getFil_id()).orElseThrow(() -> new EntityNotFoundException("Filiere_langue not found"));
        composante.addFiliere_langue(filiere_langue);
        filiere_langue.setComposante(composante);
        filiere_langueRepository.save(filiere_langue);
        composante = composanteRepository.save(composante);
        return composanteEntityToDto(composante);
    }

    @Override
    public ComposanteDto addResponsable(ComposanteDto composanteDto) {
        Composante composante = composanteRepository.findById(composanteDto.getCom_id()).orElseThrow(() -> new EntityNotFoundException("Composante not found"));
        Responsable responsable = responsableRepository.findById(composanteDto.getResponsableDtos().get(0).getUti_id()).orElseThrow(() -> new EntityNotFoundException("Responsable not found"));
        composante.addResponsable(responsable);
        responsable.setComposante(composante);
        responsableRepository.save(responsable);
        composante = composanteRepository.save(composante);
        return composanteEntityToDto(composante);
    }

    @Override
    public ComposanteDto removeFiliere_langue(ComposanteDto composanteDto) {
        Composante composante = composanteRepository.findById(composanteDto.getCom_id()).orElseThrow(() -> new EntityNotFoundException("Composante not found"));
        Filiere_langue filiere_langue = filiere_langueRepository.findById(composanteDto.getFiliere_langueDtos().get(0).getFil_id()).orElseThrow(() -> new EntityNotFoundException("Filiere_langue not found"));
        composante.removeFiliere_langue(filiere_langue);
        filiere_langue.setComposante(null);
        filiere_langueRepository.save(filiere_langue);
        composante = composanteRepository.save(composante);
        return composanteEntityToDto(composante);
    }

    @Override
    public ComposanteDto removeResponsable(ComposanteDto composanteDto) {
        Composante composante = composanteRepository.findById(composanteDto.getCom_id()).orElseThrow(() -> new EntityNotFoundException("Composante not found"));
        Responsable responsable = responsableRepository.findById(composanteDto.getResponsableDtos().get(0).getUti_id()).orElseThrow(() -> new EntityNotFoundException("Responsable not found"));
        composante.removeResponsable(responsable);
        responsable.setComposante(null);
        responsableRepository.save(responsable);
        composante = composanteRepository.save(composante);
        return composanteEntityToDto(composante);
    }

    private Composante composanteDtoToEntity(ComposanteDto composanteDto){
        Composante composante = new Composante();
        composante.setCom_id(composanteDto.getCom_id());
        composante.setNomComposante(composanteDto.getNomComposante());
        composante.setResponsables(new ArrayList<>());
        composante.setFiliere_langues(new ArrayList<>());
        return composante;
    }

    private ComposanteDto composanteEntityToDto(Composante composante){
        ComposanteDto composanteDto = new ComposanteDto();
        composanteDto.setCom_id(composante.getCom_id());
        composanteDto.setNomComposante(composante.getNomComposante());

        List<Filiere_langueDto> filiere_langueDtos = new ArrayList<>();
        for(Filiere_langue filiere_langue : composante.getFiliere_langues()){
            Filiere_langueDto filiere_langueDto = new Filiere_langueDto();
            filiere_langueDto.setFil_id(filiere_langue.getFil_id());
            filiere_langueDto.setCode(filiere_langue.getCode());
            filiere_langueDto.setNom(filiere_langue.getNom());
            filiere_langueDtos.add(filiere_langueDto);
        }
        composanteDto.setFiliere_langueDtos(filiere_langueDtos);

        List<ResponsableDto> responsableDtos = new ArrayList<>();
        for(Responsable responsable : composante.getResponsables()){
            ResponsableDto utilisateurDto = new ResponsableDto();
            utilisateurDto.setUti_id(responsable.getUti_id());
            utilisateurDto.setLogin(responsable.getLogin());
            utilisateurDto.setMdp(responsable.getMdp());
            utilisateurDto.setNom(responsable.getNom());
            utilisateurDto.setPrenom(responsable.getPrenom());
            utilisateurDto.setMail(responsable.getMail());
            responsableDtos.add(utilisateurDto);
        }
        composanteDto.setResponsableDtos(responsableDtos);
        return composanteDto;
    }
}
