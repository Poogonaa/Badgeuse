package com.controllers;

import com.dtos.GestionnaireDto;
import com.services.impl.GestionnaireServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gestionnaires")
public class GestionnaireController {
    private final GestionnaireServiceImpl gestionnaireService;

    public GestionnaireController(GestionnaireServiceImpl gestionnaireService) {
        this.gestionnaireService = gestionnaireService;
    }

    @PostMapping
    public GestionnaireDto addGestionnaire(final @RequestBody GestionnaireDto utilisateurDto){
        return gestionnaireService.newGestionnaire(utilisateurDto);
    }

    @GetMapping("/multi")
    public List<GestionnaireDto> getGestionnaire() {
        return gestionnaireService.getAllGestionnaires();
    }

    @GetMapping("/{id}")
    public GestionnaireDto getGestonnaire(@PathVariable Long id){
        return gestionnaireService.getGestionnaireById(id);
    }

    @PutMapping
    public GestionnaireDto editGestionnaire(final @RequestBody GestionnaireDto gestionnaireDto){
        return gestionnaireService.editGestionnaire(gestionnaireDto);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteGestionnaire(@PathVariable Long id){
        return gestionnaireService.deleteGestionnaire(id);
    }
}
