package com.controllers;

import com.dtos.ComposanteDto;
import com.dtos.CreneauDto;
import com.services.impl.CreneauServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/creneaux")
public class CreneauController {
    private final CreneauServiceImpl creneauService;

    public CreneauController(CreneauServiceImpl creneauService) {
        this.creneauService = creneauService;
    }

    @GetMapping("/multi")
    public List<CreneauDto> getCreneaux(){
        return creneauService.getAllCreneaux();
    }

    @GetMapping("/{id}")
    public CreneauDto getCreneau(@PathVariable Long id){
        return creneauService.getCreneauById(id);
    }

    @PutMapping
    public CreneauDto editCreneau(final @RequestBody CreneauDto creneauDto){
        return creneauService.editCreneau(creneauDto);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteCreneau(@PathVariable Long id){
        return creneauService.deleteCreneau(id);
    }

    @PostMapping
    public CreneauDto addCreneau(final @RequestBody CreneauDto creneauDto){
        return  creneauService.addCreneau(creneauDto);
    }
}
