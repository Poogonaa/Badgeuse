package com.controllers;

import com.dtos.ResponsableDto;
import com.dtos.SeanceFormationDto;
import com.dtos.UtilisateurDto;
import com.services.impl.SeanceFormationServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class SeanceFormationController {

    private final SeanceFormationServiceImpl seanceFormationService;

    public SeanceFormationController(SeanceFormationServiceImpl seanceFormationService) {
        this.seanceFormationService = seanceFormationService;
    }
    @GetMapping("/multi")
    public List<SeanceFormationDto> getSeanceFormations(){
        return seanceFormationService.getAllSeancesFormations();
    }

    @GetMapping("/{id}")
    public SeanceFormationDto getSeanceFormation(@PathVariable Long id){
        return seanceFormationService.getSeanceFormationById(id);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteSeanceFormation(@PathVariable Long id){
        return seanceFormationService.deleteSeanceFormation(id);
    }

    @PostMapping
    public SeanceFormationDto addSeanceFormation(final @RequestBody SeanceFormationDto seanceFormationDto){
        return seanceFormationService.newSeanceFormation(seanceFormationDto);
    }

    @PutMapping("/addIntervenant")
    public SeanceFormationDto addIntervenant(final @RequestBody SeanceFormationDto seanceFormationDto){
        return seanceFormationService.addIntervenant(seanceFormationDto);
    }

    @PutMapping("/removeIntervenant")
    public SeanceFormationDto removeIntervenant(final @RequestBody SeanceFormationDto seanceFormationDto){
        return seanceFormationService.removeIntervenant(seanceFormationDto);
    }

    @PutMapping("/addCreneau")
    public SeanceFormationDto addInCreneau(final @RequestBody SeanceFormationDto seanceFormationDto){
        return seanceFormationService.addCreneau(seanceFormationDto);
    }

    @PutMapping("/removeCreneau")
    public SeanceFormationDto removeCreneau(final @RequestBody SeanceFormationDto seanceFormationDto){
        return seanceFormationService.removeCreneau(seanceFormationDto);
    }
}
