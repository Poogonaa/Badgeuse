package com.controllers;

import com.dtos.CoursDto;
import com.dtos.Filiere_langueDto;
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
        return coursService.addCours(coursDto);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteCours(@PathVariable Long id){
        return coursService.deleteCours(id);
    }

    @PutMapping
    public CoursDto updateCours(final @RequestBody CoursDto coursDto){ return coursService.editCours(coursDto);}
}
