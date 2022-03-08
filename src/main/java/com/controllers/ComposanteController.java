package com.controllers;

import com.dtos.ComposanteDto;
import com.entities.Composante;
import com.services.impl.ComposanteServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/composantes")

public class ComposanteController {

    private final ComposanteServiceImpl composanteService;

    public ComposanteController(ComposanteServiceImpl composanteService){
        this.composanteService = composanteService;
    }

    @GetMapping("/multi")
    public List<ComposanteDto> getComposantes(){
        return composanteService.getAllComposantes();
    }

    @GetMapping("/mono")
    public ComposanteDto getComposante(final @RequestBody ComposanteDto composanteDto){
        return composanteService.getComposanteById(composanteDto);
    }

    @PutMapping
    public ComposanteDto editComposante(final @RequestBody ComposanteDto composanteDto){
        return composanteService.editComposante(composanteDto);
    }

    @DeleteMapping
    public Boolean deleteComposante(final @RequestBody ComposanteDto composanteDto){
        return composanteService.deleteComposante(composanteDto);
    }

    @PostMapping
    public ComposanteDto addComposante(final @RequestBody ComposanteDto composanteDto){
        return  composanteService.addComposante(composanteDto);
    }
}

