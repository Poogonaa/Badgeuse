package com.controllers;

import com.dtos.UtilisateurDto;
import com.services.impl.ResponsableServiceImpl;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/responsables")
public class ResponsableController {

    private final ResponsableServiceImpl responsableService;

    public ResponsableController(ResponsableServiceImpl responsableService) {
        this.responsableService = responsableService;
    }

    @PatchMapping
    public UtilisateurDto addResponsable(final @RequestBody UtilisateurDto utilisateurDto){
        return responsableService.addResponsable(utilisateurDto);
    }
}
