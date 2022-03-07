package com.controllers;

import com.dtos.UtilisateurDto;
import org.springframework.web.bind.annotation.*;

import com.services.impl.UtilisateurServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UtilisateurController {

    private final UtilisateurServiceImpl userService;

    public UtilisateurController(UtilisateurServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UtilisateurDto> getUtilisateurs() {
        return userService.getAllUtilisateurs();
    }

    @GetMapping("/{id}")
    public UtilisateurDto getUtilisateur(@PathVariable Long id){
        return userService.getUtilisateurById(id);
    }

    @PostMapping
    public UtilisateurDto addUtilisateur(final @RequestBody UtilisateurDto utilisateurDto){
        return userService.addUtilisateur(utilisateurDto);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteUtilisateur(@PathVariable Long id){
        return userService.deleteUtilisateur(id);
    }
}
