package com.controllers;

import com.dtos.ResponsableDto;
import com.dtos.UtilisateurDto;
import com.services.impl.ResponsableServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/responsables")
public class ResponsableController {

    private final ResponsableServiceImpl responsableService;

    public ResponsableController(ResponsableServiceImpl responsableService) {
        this.responsableService = responsableService;
    }

    @PostMapping
    public UtilisateurDto addResponsable(final @RequestBody ResponsableDto utilisateurDto){
        return responsableService.newResponsable(utilisateurDto);
    }

    @GetMapping("/multi")
    public List<ResponsableDto> getResponsable() {
        return responsableService.getAllResponsables();
    }

    @GetMapping("/{id}")
    public ResponsableDto getResponsable(@PathVariable Long id){
        return responsableService.getResponsableById(id);
    }

    @PutMapping
    public ResponsableDto editResponsable(final @RequestBody ResponsableDto responsableDto){
        return responsableService.editResponsable(responsableDto);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteResponsable(@PathVariable Long id){
        return responsableService.deleteResponsable(id);
    }

    @PutMapping("/addComposante")
    public UtilisateurDto addComposante(final @RequestBody ResponsableDto utilisateurDto){
        return responsableService.addComposante(utilisateurDto);
    }

    @PutMapping("/removeComposante")
    public UtilisateurDto removeComposante(final @RequestBody ResponsableDto utilisateurDto){
        return responsableService.removeComposante(utilisateurDto);
    }

}
