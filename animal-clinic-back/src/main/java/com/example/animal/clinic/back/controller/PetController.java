package com.example.animal.clinic.back.controller;

import com.example.animal.clinic.back.dto.PetAppointmentsDto;
import com.example.animal.clinic.back.dto.PetDto;
import com.example.animal.clinic.back.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pets")
@RequiredArgsConstructor
public class PetController {
    private final PetService petService;

    @GetMapping
    public List<PetDto> getPets() {
        return petService.getPets();
    }

    @PostMapping
    public PetDto addPet(@RequestBody PetDto petDto) {
        return petService.addPet(petDto);
    }

    @PutMapping
    public PetDto updatePet(@RequestBody PetDto petDto) {
        return petService.updatePet(petDto);
    }

    @DeleteMapping("/{petId}")
    public Long deletePet(@PathVariable Long petId) {
        return petService.deletePet(petId);
    }

    @GetMapping("/appointments")
    public List<PetAppointmentsDto> getPetsAppointments() {
        return petService.getPetsAppointments();
    }
}
