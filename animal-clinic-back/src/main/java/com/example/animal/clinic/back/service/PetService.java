package com.example.animal.clinic.back.service;

import com.example.animal.clinic.back.dto.PetAppointmentsDto;
import com.example.animal.clinic.back.dto.PetDto;

import java.util.List;

public interface PetService {
    List<PetDto> getPets();

    PetDto addPet(PetDto petDto);

    PetDto updatePet(PetDto petDto);

    Long deletePet(Long petId);

    List<PetAppointmentsDto> getPetsAppointments();
}
