package com.controllers;

import com.dtos.Filiere_langueDto;
import com.dtos.SeanceFormationDto;
import com.services.impl.SeanceFormationServiceImpl;
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

    @PutMapping
    public SeanceFormationDto editSeanceFormation(final @RequestBody SeanceFormationDto seanceFormationDto){
        return seanceFormationService.editSeanceFormation(seanceFormationDto);
    }

    @GetMapping("/intervenant/{id}")
    public List<SeanceFormationDto> getSeanceFormationsByIntervenant(@PathVariable Long id){
        return seanceFormationService.getAllSeancesFormationsByIntervenant(id);
    }

    @GetMapping("/effectue/intervenant/{id}")
    public List<SeanceFormationDto> getSeanceFormationEffectueesByIntervenant(@PathVariable Long id){
        return seanceFormationService.getAllSeancesFormationsEffectueesByIntervenant(id);
    }

    @GetMapping("/valide/intervenant")
    public List<SeanceFormationDto> getSeanceFormationValide(){
        return seanceFormationService.getAllSeancesFormationsValide();
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

    @PutMapping("/valider")
    public SeanceFormationDto valider(final @RequestBody SeanceFormationDto seanceFormationDto){
        return seanceFormationService.valider(seanceFormationDto);
    }
}
