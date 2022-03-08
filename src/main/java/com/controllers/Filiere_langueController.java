package com.controllers;

import com.dtos.Filiere_langueDto;
import com.services.impl.Filiere_langueServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filiere_langues")
public class Filiere_langueController {

    private final Filiere_langueServiceImpl filiere_langueService;

    public Filiere_langueController(Filiere_langueServiceImpl filiere_langueService) {
        this.filiere_langueService = filiere_langueService;
    }

    @PostMapping
    public Filiere_langueDto addFiliere_langue(final @RequestBody Filiere_langueDto filiere_langueDto){
        return filiere_langueService.addFiliere_langue(filiere_langueDto);
    }

    @GetMapping("/tout")
    public List<Filiere_langueDto> getFiliere_langue() {
        return filiere_langueService.getAllFiliere_langues();
    }

    @GetMapping("/un")
    public Filiere_langueDto getFiliere_langue(final @RequestBody Filiere_langueDto filiere_langueDto){
        return filiere_langueService.getFiliere_langueById(filiere_langueDto);
    }

    @PutMapping
    public Filiere_langueDto editFiliere_langue(final @RequestBody Filiere_langueDto filiere_langueDto){
        return filiere_langueService.editFiliere_langue(filiere_langueDto);
    }

    @DeleteMapping
    public Boolean deleteFiliere_langue(final @RequestBody Filiere_langueDto filiere_langueDto){
        return filiere_langueService.deleteFiliere_langue(filiere_langueDto);
    }
}
