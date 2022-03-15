package com.controllers;

import com.dtos.SeanceFormationDto;
import com.repositories.IntervenantRepository;
import com.services.impl.SeanceFormationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("seancesformations")
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

    @GetMapping("/intervenant/{id}")
    public List<SeanceFormationDto> getSeanceFormationsByIntervenant(@PathVariable Long id){
        return seanceFormationService.getAllSeancesFormationsByIntervenant(id);
    }

    @GetMapping("/effectue/intervenant/{id}")
    public List<SeanceFormationDto> getSeanceFormationEffectueesByIntervenant(@PathVariable Long id){
        return seanceFormationService.getAllSeancesFormationsEffectueesByIntervenant(id);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteSeanceFormation(@PathVariable Long id){
        return seanceFormationService.deleteSeanceFormation(id);
    }

    @PostMapping
    public SeanceFormationDto addSeanceFormation(final @RequestBody SeanceFormationDto seanceFormationDto){
        return seanceFormationService.addSeanceFormation(seanceFormationDto);
    }

    @PutMapping("/valider")
    public SeanceFormationDto valider(final @RequestBody SeanceFormationDto seanceFormationDto){
        return seanceFormationService.valider(seanceFormationDto);
    }
}
