package com.example.animal.clinic.back.service;

import com.example.animal.clinic.back.dto.MedicalServiceAppointmentDto;
import com.example.animal.clinic.back.dto.PetAppointmentsDto;
import com.example.animal.clinic.back.dto.PetDto;
import com.example.animal.clinic.back.dto.SpecialistDto;
import com.example.animal.clinic.back.entity.MedicalService;
import com.example.animal.clinic.back.entity.Pet;
import com.example.animal.clinic.back.entity.PetTypeEnum;
import com.example.animal.clinic.back.entity.Specialist;
import com.example.animal.clinic.back.entity.User;
import com.example.animal.clinic.back.exception.PetNotFoundException;
import com.example.animal.clinic.back.repository.PetRepository;
import com.example.animal.clinic.back.repository.PetTypeRepository;
import com.example.animal.clinic.back.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PetServiceImpl implements PetService {
    private final UserRepository userRepository;
    private final PetRepository petRepository;
    private final PetTypeRepository petTypeRepository;

    @Override
    public List<PetDto> getPets() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        return petRepository
                .findAllByUserEmailOrderByName(email)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public PetDto addPet(PetDto petDto) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        Pet pet = Pet.builder()
                .name(petDto.getName())
                .petType(petTypeRepository.findByName(PetTypeEnum.valueOf(petDto.getPetType())))
                .birthDate(petDto.getBirthDate())
                .user(user)
                .breed(petDto.getBreed())
                .build();

        petRepository.save(pet);
        log.info("New pet {} was added.", petDto.toString());
        return convertToDto(pet);
    }

    @Override
    public PetDto updatePet(PetDto petDto) {
        Pet pet = petRepository.findById(petDto.getId())
                .orElseThrow(() -> new PetNotFoundException("Pet not found with id: " + petDto.getId()));

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        if (!userRepository.existsByEmailAndPetsContains(email, pet)) {
            throw new IllegalArgumentException("User doesnt have pet with id: " + petDto.getId());
        }

        pet.setName(petDto.getName());
        pet.setPetType(petTypeRepository.findByName(PetTypeEnum.valueOf(petDto.getPetType())));
        pet.setBreed(pet.getBreed());
        pet.setBirthDate(petDto.getBirthDate());

        petRepository.save(pet);
        log.info("Pet with id {} was updated.", pet.getId());

        return convertToDto(pet);
    }

    @Override
    public Long deletePet(Long petId) {
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new PetNotFoundException("Pet not found with id: " + petId));

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        if (!userRepository.existsByEmailAndPetsContains(email, pet)) {
            throw new IllegalArgumentException("You dont have pet with id: " + petId);
        }

        petRepository.deleteById(petId);
        log.info("Pet with id {} was deleted.", pet.getId());

        return petId;
    }

    @Override
    public List<PetAppointmentsDto> getPetsAppointments() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return user.getPets().stream()
                .map(this::convertToPetAppointmentsDto)
                .sorted(Comparator.comparing(PetAppointmentsDto::getName))
                .collect(Collectors.toList());
    }

    private PetDto convertToDto(Pet pet) {
        return PetDto.builder()
                .id(pet.getId())
                .name(pet.getName())
                .petType(pet.getPetType().getName().name())
                .breed(pet.getBreed())
                .birthDate(pet.getBirthDate())
                .build();
    }

    private PetAppointmentsDto convertToPetAppointmentsDto(Pet pet) {
        return PetAppointmentsDto.builder()
                .id(pet.getId())
                .name(pet.getName())
                .petType(pet.getPetType().getName().name())
                .breed(pet.getBreed())
                .birthDate(pet.getBirthDate())
                .appointments(pet.getMedicalServices().stream()
                        .map(this::convertToAppointmentDto)
                        .sorted(Comparator.comparing(MedicalServiceAppointmentDto::getTime).reversed())
                        .sorted(Comparator.comparing(MedicalServiceAppointmentDto::getDate).reversed())
                        .collect(Collectors.toList()))
                .build();
    }

    private MedicalServiceAppointmentDto convertToAppointmentDto(MedicalService medicalService) {
        return MedicalServiceAppointmentDto.builder()
                .id(medicalService.getId())
                .name(medicalService.getMedicalServiceType().getName())
                .specialist(convertToDto(medicalService.getSpecialist()))
                .date(medicalService.getDate())
                .time(medicalService.getTime())
                .officeNumber(medicalService.getOfficeNumber())
                .result(medicalService.getResult())
                .build();
    }

    private SpecialistDto convertToDto(Specialist specialist) {
        if (isNull(specialist))
            return null;
        return SpecialistDto.builder()
                .firstName(specialist.getFirstName())
                .lastName(specialist.getLastName())
                .patronymic(specialist.getPatronymic())
                .build();
    }
}
