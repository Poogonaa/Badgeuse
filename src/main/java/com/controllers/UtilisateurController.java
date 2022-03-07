package com.controllers;

import com.dtos.IntervenantDto;
import org.springframework.web.bind.annotation.*;

import com.services.impl.IntervenantServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/intervenants")
public class IntervenantController {

    private final IntervenantServiceImpl intervenantService;

    public IntervenantController(IntervenantServiceImpl intervenantService) {
        this.intervenantService = intervenantService;
    }

    @GetMapping
    public List<IntervenantDto> getIntervenant() {
        return intervenantService.getAllIntervenants();
    }

    @GetMapping("/{id}")
    public IntervenantDto getIntervenant(@PathVariable Long id){
        return intervenantService.getIntervenantById(id);
    }

    @PostMapping
    public IntervenantDto addIntervenant(final @RequestBody IntervenantDto intervenantDto){
        return intervenantService.addIntervenant(intervenantDto);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteIntervenant(@PathVariable Long id){
        return intervenantService.deleteIntervenant(id);
    }
}
