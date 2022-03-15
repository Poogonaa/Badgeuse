package com.controllers;

import com.dtos.ComposanteDto;
import com.dtos.CoursDto;
import com.dtos.IntervenantDto;
import org.springframework.web.bind.annotation.*;

import com.services.impl.CoursServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/cours")
public class CoursController {

    private final CoursServiceImpl coursService;

    public CoursController(CoursServiceImpl coursService) {
        this.coursService = coursService;
    }

    @GetMapping("/multi")
    public List<CoursDto> getAllCours() {
        return coursService.getAllCours();
    }

    @GetMapping("/{id}")
    public CoursDto getCours(@PathVariable Long id){
        return coursService.getCoursById(id);
    }

    @PostMapping
    public CoursDto addCours(final @RequestBody CoursDto coursDto){
        return coursService.newCours(coursDto);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteCours(@PathVariable Long id){
        return coursService.deleteCours(id);
    }

    @PutMapping
    public CoursDto updateCours(final @RequestBody CoursDto coursDto){ return coursService.editCours(coursDto);}

    @PutMapping("/addIntervenant")
    public CoursDto addIntervenant(final @RequestBody CoursDto coursDto){
        return coursService.addIntervenant(coursDto);
    }

    @PutMapping("/removeIntervenant")
    public CoursDto removeIntervenant(final @RequestBody CoursDto coursDto){
        return coursService.removeIntervenant(coursDto);
    }

    @PutMapping("/addFiliere_langue")
    public CoursDto addFiliere_langue(final @RequestBody CoursDto coursDto){
        return coursService.addFiliere_langue(coursDto);
    }

    @PutMapping("/removeFiliere_langue")
    public CoursDto removeFiliere_langue(final @RequestBody CoursDto coursDto){
        return coursService.removeFiliere_langue(coursDto);
    }

    @PutMapping("/addCreneau")
    public CoursDto addCreneau(final @RequestBody CoursDto coursDto){
        return coursService.addCreneau(coursDto);
    }

    @PutMapping("/removeCreneau")
    public CoursDto removeCreneau(final @RequestBody CoursDto coursDto){
        return coursService.removeCreneau(coursDto);
    }
}
