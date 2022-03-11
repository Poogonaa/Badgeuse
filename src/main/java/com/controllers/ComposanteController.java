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
    public ComposanteDto addComposante(final @RequestBody ComposanteDto composanteDto){
        return  composanteService.addComposante(composanteDto);
    }
}

