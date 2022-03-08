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

    @GetMapping("/mono")
    public CreneauDto getCreneau(final @RequestBody CreneauDto creneauDto){
        return creneauService.getCreneauById(creneauDto);
    }

    @PutMapping
    public CreneauDto editCreneau(final @RequestBody CreneauDto creneauDto){
        return creneauService.editCreneau(creneauDto);
    }

    @DeleteMapping
    public Boolean deleteCreneau(final @RequestBody CreneauDto creneauDto){
        return creneauService.deleteCreneau(creneauDto);
    }

    @PostMapping
    public CreneauDto addCreneau(final @RequestBody CreneauDto creneauDto){
        return  creneauService.addCreneau(creneauDto);
    }
}
