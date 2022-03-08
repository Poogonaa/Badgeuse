package com.controllers;

import com.dtos.UtilisateurDto;
import com.services.impl.IntervenantServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/intervenants")
public class IntervenantController {
    private final IntervenantServiceImpl intervenantService;

    public IntervenantController(IntervenantServiceImpl intervenantService) {
        this.intervenantService = intervenantService;
    }

    @PostMapping
    public UtilisateurDto addIntervenant(final @RequestBody UtilisateurDto utilisateurDto){
        return intervenantService.addIntervenant(utilisateurDto);
    }
}
