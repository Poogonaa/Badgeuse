package com.controllers;

import com.dtos.IntervenantDto;
import com.services.impl.IntervenantServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/intervenants")
public class IntervenantController {
    private final IntervenantServiceImpl intervenantService;

    public IntervenantController(IntervenantServiceImpl intervenantService) {
        this.intervenantService = intervenantService;
    }

    @PostMapping
    public IntervenantDto addIntervenant(final @RequestBody IntervenantDto intervenantDto){
        return intervenantService.newIntervenant(intervenantDto);
    }

    @GetMapping("/{id}")
    public IntervenantDto getIntervenant(@PathVariable Long id){
        return intervenantService.getIntervenantById(id);
    }

    @GetMapping("/multi")
    public List<IntervenantDto> getIntervenants() {
        return intervenantService.getAllIntervenants();
    }


    @PutMapping
    public IntervenantDto editIntervenant(final @RequestBody IntervenantDto intervenantDto){
        return intervenantService.editIntervenant(intervenantDto);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteIntervenant(@PathVariable Long id){
        return intervenantService.deleteIntervenant(id);
    }

    @PutMapping("/addCours")
    public IntervenantDto addCours(final @RequestBody IntervenantDto intervenantDto){
        return intervenantService.addCours(intervenantDto);
    }

    @PutMapping("/removeCours")
    public IntervenantDto removeCrous(final @RequestBody IntervenantDto intervenantDto){
        return intervenantService.removeCours(intervenantDto);
    }

    @PutMapping("/addSeanceFormation")
    public IntervenantDto addSeanceFormation(final @RequestBody IntervenantDto intervenantDto){
        return intervenantService.addSeanceFormation(intervenantDto);
    }

    @PutMapping("/removeSeanceFormation")
    public IntervenantDto removeSeanceFormation(final @RequestBody IntervenantDto intervenantDto){
        return intervenantService.removeSeanceFormation(intervenantDto);
    }
}
