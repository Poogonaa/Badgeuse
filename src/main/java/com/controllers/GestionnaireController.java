package com.controllers;

import com.dtos.GestionnaireDto;
import com.dtos.UtilisateurDto;
import com.services.impl.GestionnaireServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gestionnaires")
public class GestionnaireController {
    private final GestionnaireServiceImpl gestionnaireService;

    public GestionnaireController(GestionnaireServiceImpl gestionnaireService) {
        this.gestionnaireService = gestionnaireService;
    }

    @PostMapping
    public UtilisateurDto addGestionnaire(final @RequestBody GestionnaireDto utilisateurDto){
        return gestionnaireService.newGestionnaire(utilisateurDto);
    }
}
