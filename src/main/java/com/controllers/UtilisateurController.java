package com.controllers;

import com.dtos.UtilisateurDto;
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

    @GetMapping("/multi")
    public List<UtilisateurDto> getUtilisateur() {
        return utilisateurService.getAllUtilisateurs();
    }

    @GetMapping("/{id}")
    public UtilisateurDto getUtilisateur(@PathVariable Long id){
        return utilisateurService.getUtilisateurById(id);
    }

    @PostMapping("/login")
    public UtilisateurDto connection(final @RequestBody UtilisateurDto utilisateurDto){
        return utilisateurService.connection(utilisateurDto);
    }
}
