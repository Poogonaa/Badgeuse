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

    @GetMapping("/mono")
    public CoursDto getCours(final @RequestBody CoursDto coursDto){
        return coursService.getCoursById(coursDto);
    }

    @PostMapping
    public CoursDto addCours(final @RequestBody CoursDto coursDto){
        return coursService.addCours(coursDto);
    }

    @DeleteMapping
    public Boolean deleteCours(final @RequestBody CoursDto coursDto){
        return coursService.deleteCours(coursDto);
    }

    @PutMapping
    public CoursDto updateCours(final @RequestBody CoursDto coursDto){ return coursService.editCours(coursDto);}
}
