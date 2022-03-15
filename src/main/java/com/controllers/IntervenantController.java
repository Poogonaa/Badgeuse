package com.controllers;

import com.dtos.Filiere_langueDto;
import com.dtos.IntervenantDto;
import com.dtos.UtilisateurDto;
import com.services.impl.IntervenantServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/intervenants")
public class IntervenantController {
    private final IntervenantServiceImpl intervenantService;

    public IntervenantController(IntervenantServiceImpl intervenantService) {
        this.intervenantService = intervenantService;
    }

    @PostMapping
    public UtilisateurDto addIntervenant(final @RequestBody IntervenantDto utilisateurDto){
        return intervenantService.newIntervenant(utilisateurDto);
    }

    @PutMapping("/addCours")
    public UtilisateurDto addCours(final @RequestBody IntervenantDto utilisateurDto){
        return intervenantService.addCours(utilisateurDto);
    }

    @PutMapping("/removeCours")
    public UtilisateurDto removeCrous(final @RequestBody IntervenantDto utilisateurDto){
        return intervenantService.removeCours(utilisateurDto);
    }

    @PutMapping("/addSeanceFormation")
    public UtilisateurDto addSeanceFormation(final @RequestBody IntervenantDto utilisateurDto){
        return intervenantService.addSeanceFormation(utilisateurDto);
    }

    @PutMapping("/removeSeanceFormation")
    public UtilisateurDto removeSeanceFormation(final @RequestBody IntervenantDto utilisateurDto){
        return intervenantService.removeSeanceFormation(utilisateurDto);
    }
}
