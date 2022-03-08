package com.controllers;

import com.dtos.UtilisateurDto;
import com.services.impl.GestionnaireServiceImpl;
import com.services.impl.UtilisateurServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utilisateurs")
public class UtilisateurController {

    private  final UtilisateurServiceImpl utilisateurService;

    public UtilisateurController(UtilisateurServiceImpl utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping("/tout")
    public List<UtilisateurDto> getUtilisateurs() {
        return utilisateurService.getAllUtilisateurs();
    }

    @GetMapping("/un")
    public UtilisateurDto getUtilisateur(final @RequestBody UtilisateurDto utilisateurDto){
        return utilisateurService.getUtilisateurById(utilisateurDto);
    }

    @PutMapping
    public UtilisateurDto editUtilisateur(final @RequestBody UtilisateurDto utilisateurDto){
        return utilisateurService.editUtilisateur(utilisateurDto);
    }

    @DeleteMapping
    public Boolean deleteUtilisateur(final @RequestBody UtilisateurDto utilisateurDto){
        return utilisateurService.deleteUtilisateur(utilisateurDto);
    }
}
