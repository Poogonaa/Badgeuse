package com.controllers;

import com.dtos.ResponsableDto;
import com.dtos.UtilisateurDto;
import com.services.impl.ResponsableServiceImpl;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/addComposante")
    public UtilisateurDto addComposante(final @RequestBody ResponsableDto utilisateurDto){
        return responsableService.addComposante(utilisateurDto);
    }

    @PutMapping("/removeComposante")
    public UtilisateurDto removeComposante(final @RequestBody ResponsableDto utilisateurDto){
        return responsableService.removeComposante(utilisateurDto);
    }

}
