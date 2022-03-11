package com.controllers;

import com.dtos.SeanceFormationDto;
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
        return seanceFormationService.addSeanceFormation(seanceFormationDto);
    }
}
