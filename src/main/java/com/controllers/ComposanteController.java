package com.controllers;

import com.dtos.ComposanteDto;
import com.services.impl.ComposanteServiceImpl;
import com.services.impl.Filiere_langueServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/composantes")
public class ComposanteController {

    private final ComposanteServiceImpl composanteService;
    private final Filiere_langueServiceImpl filiere_langueService;

    public ComposanteController(ComposanteServiceImpl composanteService, Filiere_langueServiceImpl filiere_langueService){
        this.composanteService = composanteService;
        this.filiere_langueService = filiere_langueService;
    }

    @GetMapping("/multi")
    public List<ComposanteDto> getComposantes(){
        return composanteService.getAllComposantes();
    }

    @GetMapping("/{id}")
    public ComposanteDto getComposante(@PathVariable Long id){
        return composanteService.getComposanteById(id);
    }

    @PutMapping
    public ComposanteDto editComposante(final @RequestBody ComposanteDto composanteDto){
        return composanteService.editComposante(composanteDto);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteComposante(@PathVariable Long id){
        return composanteService.deleteComposante(id);
    }

    @PostMapping
    public ComposanteDto newComposante(final @RequestBody ComposanteDto composanteDto){
        return  composanteService.newComposante(composanteDto);
    }

    @PutMapping("/addFiliere_langue")
    public ComposanteDto addFiliere_langue(final @RequestBody ComposanteDto composanteDto){
        return composanteService.addFiliere_langue(composanteDto);
    }

    @PutMapping("/removeFiliere_langue")
    public ComposanteDto removeFiliere_langue(final @RequestBody ComposanteDto composanteDto){
        return composanteService.removeFiliere_langue(composanteDto);
    }

    @PutMapping("/addResponsable")
    public ComposanteDto addResponsable(final @RequestBody ComposanteDto composanteDto){
        return composanteService.addResponsable(composanteDto);
    }

    @PutMapping("/removeResponsable")
    public ComposanteDto removeResponsable(final @RequestBody ComposanteDto composanteDto){
        return composanteService.removeResponsable(composanteDto);
    }
}

