package org.example.clinicservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.clinicservice.dto.SpecialistDTO;
import org.example.clinicservice.entity.Specialist;
import org.example.clinicservice.service.interfeces.SpecialistService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/specialist")
@RequiredArgsConstructor
public class SpecialistController {

    private final SpecialistService specialistService;

    @GetMapping("/get/{id}")
    public Specialist getSpecialistById(@PathVariable("id") UUID id){
        return specialistService.getSpecialistById(id);
    }
    @PostMapping("/create")
    public Specialist createSpecialist(@RequestBody SpecialistDTO specialistDTO) {
        return specialistService.createSpecialist(specialistDTO);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSpecialist(@PathVariable("id") UUID id) {
        specialistService.deleteSpecialist(id);
    }
}
