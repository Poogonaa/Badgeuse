package com.services.impl;

import com.dtos.CreneauDto;
import com.dtos.IntervenantDto;
import com.dtos.SeanceFormationDto;
import com.entities.*;
import com.repositories.CreneauRepository;
import com.entities.SeanceFormation;
import com.repositories.IntervenantRepository;
import com.repositories.SeanceFormationRepository;
import com.repositories.UtilisateurRepository;
import com.services.SeanceFormationService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service("seanceFormationService")
public class SeanceFormationServiceImpl implements SeanceFormationService {

    protected final SeanceFormationRepository seanceFormationRepository;
    protected final IntervenantRepository intervenantRepository;
    protected final CreneauRepository creneauRepository;

    public SeanceFormationServiceImpl(SeanceFormationRepository seanceFormationRepository, IntervenantRepository intervenantRepository, CreneauRepository creneauRepository) {
        this.seanceFormationRepository = seanceFormationRepository;
        this.intervenantRepository = intervenantRepository;
        this.creneauRepository = creneauRepository;
    }

    @Override
    public SeanceFormationDto getSeanceFormationById(Long id) {
        SeanceFormation seanceFormation = seanceFormationRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Seance not found"));
        return seanceFormationEntityToDto(seanceFormation);
    }

    @Override
    public boolean deleteSeanceFormation(Long id) {
        seanceFormationRepository.deleteById(id);
        return true;
    }

    @Override
    public List<SeanceFormationDto> getAllSeancesFormations() {
        List<SeanceFormationDto> seanceFormationDtos = new ArrayList<>();
        List<SeanceFormation> seanceFormations = seanceFormationRepository.findAll();
        for (SeanceFormation seanceFormation : seanceFormations) {
            seanceFormationDtos.add(seanceFormationEntityToDto(seanceFormation));
        }
        return seanceFormationDtos;
    }


    @Override
    public List<SeanceFormationDto> getAllSeancesFormationsByIntervenant(Long id){
        List<SeanceFormationDto> seanceFormationDtos = new ArrayList<>();
        List<SeanceFormation> seanceFormations = seanceFormationRepository.findAll();
        //on enlève ceux qui n'ont pas pour Intervenant celui que l'on veut
        for(SeanceFormation seance : seanceFormations){
            if(Objects.equals(seance.getIntervenant().getUti_id(), id)){
                seanceFormationDtos.add(seanceFormationEntityToDto(seance));
            }
        }
        return seanceFormationDtos;
    }

    @Override
    public List<SeanceFormationDto> getAllSeancesFormationsEffectueesByIntervenant(Long id){
        List<SeanceFormationDto> seanceFormationDtos = new ArrayList<>();
        List<SeanceFormation> seanceFormations = seanceFormationRepository.findAll();
        //on enlève ceux qui n'ont pas pour Intervenant ciblé ou qui ne sont pas effectuées.
        for(SeanceFormation seance : seanceFormations){
            if(Objects.equals(seance.getIntervenant().getUti_id(), id) && seance.getEstEffectue())
                seanceFormationDtos.add(seanceFormationEntityToDto(seance));
        }
        return seanceFormationDtos;
    }

    @Override
    public List<SeanceFormationDto> getAllSeancesFormationsValide(){
        List<SeanceFormationDto> seanceFormationDtos = new ArrayList<>();
        List<SeanceFormation> seanceFormations = seanceFormationRepository.findAll();
        for (SeanceFormation seance : seanceFormations){
            if(seance.getValide() != null && seance.getValide())
                seanceFormationDtos.add(seanceFormationEntityToDto(seance));
        }
        return seanceFormationDtos;
    }

    @Override
    public Map<Long, Integer> getHeureIntervenants(){
        Map<Long, Integer> listHeuresIntervenants = new HashMap<Long, Integer>();
        List<SeanceFormation> seanceFormations = seanceFormationRepository.findAll();
        for(SeanceFormation seance : seanceFormations){
            //on ajoute dans la liste, seulement si la séance est validée
            if(seance.getValide() != null && seance.getValide()){
                //si l'intervenant n'existe pas dans la map, on le rajoute
                if(!listHeuresIntervenants.containsKey(seance.getIntervenant().getUti_id())){
                    listHeuresIntervenants.put(seance.getIntervenant().getUti_id(), 0);
                }
                //puis, on rajoute le temps effectué
                Integer nbHeures = listHeuresIntervenants.get(seance.getIntervenant().getUti_id());
                nbHeures += seance.getDureeEffective();
                listHeuresIntervenants.put(seance.getIntervenant().getUti_id(), nbHeures);
            }
        }
        return listHeuresIntervenants;
    }

    @Override
    public SeanceFormationDto editSeanceFormation(SeanceFormationDto seanceFormationDto) {
        SeanceFormation seanceFormation = seanceFormationRepository.findById(seanceFormationDto.getSea_id()).orElseThrow(() -> new EntityNotFoundException("seance not found"));
        seanceFormation.setEstEffectue(seanceFormationDto.getEstEffectue());
        seanceFormation.setValide(seanceFormationDto.getValide());
        seanceFormation.setCommentaire(seanceFormationDto.getCommentaire());
        seanceFormation = seanceFormationRepository.save(seanceFormation);
        return seanceFormationEntityToDto(seanceFormation);
    }

    @Override
    public SeanceFormationDto newSeanceFormation(SeanceFormationDto seanceFormationDto) {
        SeanceFormation seanceFormation = seanceFormationDtoToEntity(seanceFormationDto);
        seanceFormation = seanceFormationRepository.save(seanceFormation);
        return seanceFormationEntityToDto(seanceFormation);
    }

    @Override
    public SeanceFormationDto addIntervenant(SeanceFormationDto seanceFormationDto) {
        SeanceFormation seanceFormation = seanceFormationRepository.findById(seanceFormationDto.getSea_id()).orElseThrow(() -> new EntityNotFoundException("SeanceFormation not found"));
        Intervenant intervenant = intervenantRepository.findById(seanceFormationDto.getIntervenantDto().getUti_id()).orElseThrow(() -> new EntityNotFoundException("Intervenant not found"));
        seanceFormation.setIntervenant(intervenant);
        intervenant.addSeanceFormation(seanceFormation);
        seanceFormationRepository.save(seanceFormation);
        seanceFormation = seanceFormationRepository.save(seanceFormation);
        return seanceFormationEntityToDto(seanceFormation);
    }

    @Override
    public SeanceFormationDto addCreneau(SeanceFormationDto seanceFormationDto) {
        SeanceFormation seanceFormation = seanceFormationRepository.findById(seanceFormationDto.getSea_id()).orElseThrow(() -> new EntityNotFoundException("SeanceFormation not found"));
        Creneau creneau = creneauRepository.findById(seanceFormationDto.getCreneauDto().getCre_id()).orElseThrow(() -> new EntityNotFoundException("Composante not found"));
        seanceFormation.setCreneau(creneau);
        creneau.addSeanceFormation(seanceFormation);
        creneauRepository.save(creneau);
        seanceFormation = seanceFormationRepository.save(seanceFormation);
        return seanceFormationEntityToDto(seanceFormation);
    }

    @Override
    public SeanceFormationDto removeIntervenant(SeanceFormationDto seanceFormationDto) {
        SeanceFormation seanceFormation = seanceFormationRepository.findById(seanceFormationDto.getSea_id()).orElseThrow(() -> new EntityNotFoundException("SeanceFormation not found"));
        Intervenant intervenant = intervenantRepository.findById(seanceFormationDto.getIntervenantDto().getUti_id()).orElseThrow(() -> new EntityNotFoundException("Intervenant not found"));
        seanceFormation.setIntervenant(null);
        intervenant.removeSeanceFormation(seanceFormation);
        intervenantRepository.save(intervenant);
        seanceFormation = seanceFormationRepository.save(seanceFormation);
        return seanceFormationEntityToDto(seanceFormation);
    }

    @Override
    public SeanceFormationDto removeCreneau(SeanceFormationDto seanceFormationDto) {
        SeanceFormation seanceFormation = seanceFormationRepository.findById(seanceFormationDto.getSea_id()).orElseThrow(() -> new EntityNotFoundException("SeanceFormation not found"));
        Creneau creneau = creneauRepository.findById(seanceFormationDto.getCreneauDto().getCre_id()).orElseThrow(() -> new EntityNotFoundException("Composante not found"));
        seanceFormation.setCreneau(null);
        creneau.removeSeanceFormation(seanceFormation);
        creneauRepository.save(creneau);
        seanceFormation = seanceFormationRepository.save(seanceFormation);
        return seanceFormationEntityToDto(seanceFormation);
    }

    public SeanceFormationDto valider(SeanceFormationDto seanceFormationDto){
        SeanceFormation seance = seanceFormationRepository.findById(seanceFormationDto.getSea_id()).orElseThrow(() -> new EntityNotFoundException("SeanceFormation not found"));
        seance.setValide(seanceFormationDto.getValide());
        seance = seanceFormationRepository.save(seance);
        return seanceFormationEntityToDto(seance);
    }

    private SeanceFormationDto seanceFormationEntityToDto(SeanceFormation seanceFormation){
        SeanceFormationDto seanceFormationDto = new SeanceFormationDto();
        seanceFormationDto.setSea_id(seanceFormation.getSea_id());
        seanceFormationDto.setDureeEffective(seanceFormation.getDureeEffective());
        seanceFormationDto.setValide(seanceFormation.getValide());
        seanceFormationDto.setEstEffectue(seanceFormation.getEstEffectue());
        seanceFormationDto.setCommentaire(seanceFormation.getCommentaire());

        if(seanceFormation.getIntervenant() != null){
            IntervenantDto intervenantDto = new IntervenantDto();
            intervenantDto.setUti_id(seanceFormation.getIntervenant().getUti_id());
            intervenantDto.setLogin(seanceFormation.getIntervenant().getLogin());
            intervenantDto.setMdp(seanceFormation.getIntervenant().getMdp());
            intervenantDto.setNom(seanceFormation.getIntervenant().getNom());
            intervenantDto.setPrenom(seanceFormation.getIntervenant().getPrenom());
            intervenantDto.setMail(seanceFormation.getIntervenant().getMail());
            seanceFormationDto.setIntervenantDto(intervenantDto);
        }

        if(seanceFormation.getCreneau() != null){
            CreneauDto creneauDto = new CreneauDto();
            creneauDto.setCre_id(seanceFormation.getCreneau().getCre_id());
            creneauDto.setDate(seanceFormation.getCreneau().getDate());
            creneauDto.setHeure_debut(seanceFormation.getCreneau().getHeure_debut());
            creneauDto.setDuree(seanceFormation.getCreneau().getDuree());
            creneauDto.setType(seanceFormation.getCreneau().getType());
            creneauDto.setSalle(seanceFormation.getCreneau().getSalle());
            seanceFormationDto.setCreneauDto(creneauDto);
        }
        return seanceFormationDto;
    }

    private SeanceFormation seanceFormationDtoToEntity(SeanceFormationDto seanceFormationDto){
        SeanceFormation seanceFormation = new SeanceFormation();
        seanceFormation.setSea_id(seanceFormationDto.getSea_id());
        seanceFormation.setDureeEffective(seanceFormationDto.getDureeEffective());
        seanceFormation.setValide(seanceFormationDto.getValide());
        seanceFormation.setEstEffectue(seanceFormationDto.getEstEffectue());
        seanceFormation.setCommentaire(seanceFormationDto.getCommentaire());
        seanceFormation.setIntervenant(null);
        seanceFormation.setCreneau(null);
        return seanceFormation;
    }
}
