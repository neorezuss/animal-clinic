package com.example.animal.clinic.back.service;

import com.example.animal.clinic.back.dto.PetDto;

public interface PetService {
    PetDto addPet(PetDto petDto);
    PetDto updatePet(PetDto petDto);
    boolean deletePet(Long petId);
}
