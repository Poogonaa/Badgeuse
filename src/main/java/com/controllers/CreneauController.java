package com.controllers;

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
        return  creneauService.newCreneau(creneauDto);
    }

    @PutMapping("/addCours")
    public CreneauDto addCours(final @RequestBody CreneauDto creneauDto){
        return creneauService.addCours(creneauDto);
    }

    @PutMapping("/removeCours")
    public CreneauDto removeCours(final @RequestBody CreneauDto creneauDto){
        return creneauService.removeCours(creneauDto);
    }

    @PutMapping("/addSeanceFormation")
    public CreneauDto addSeanceFormation(final @RequestBody CreneauDto creneauDto){
        return creneauService.addSeanceFormation(creneauDto);
    }

    @PutMapping("/removeSeanceFormation")
    public CreneauDto removeSeanceFormation(final @RequestBody CreneauDto creneauDto){
        return creneauService.removeSeanceFormation(creneauDto);
    }
}
