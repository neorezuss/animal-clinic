package com.example.animal.clinic.back.service;

import com.example.animal.clinic.back.dto.PetDto;
import com.example.animal.clinic.back.entity.Pet;
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

@Service
@RequiredArgsConstructor
@Slf4j
public class PetServiceImpl implements PetService {
    private final UserRepository userRepository;
    private final PetRepository petRepository;
    private final PetTypeRepository petTypeRepository;

    @Override
    public PetDto addPet(PetDto petDto) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        Pet pet = Pet.builder()
                .name(petDto.getName())
                .petType(petTypeRepository.findByName(petDto.getPetType()))
                .birthDate(petDto.getBirthDate())
                .user(user)
                .build();

        petRepository.save(pet);
        log.info("New pet {} was added.", petDto.toString());
        return petDto;
    }

    @Override
    public PetDto updatePet(PetDto petDto) {
        Pet pet = petRepository.findById(petDto.getId())
                .orElseThrow(() -> new PetNotFoundException("Pet not found with id: " + petDto.getId()));

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        if (!userRepository.existsByEmailAndPetsContains(email, pet)) {
            throw new IllegalArgumentException("You dont have pet with id: " + petDto.getId());
        }

        pet.setName(petDto.getName());
        pet.setPetType(petTypeRepository.findByName(petDto.getPetType()));
        pet.setBirthDate(petDto.getBirthDate());

        petRepository.save(pet);
        log.info("Pet with id {} was updated.", pet.getId());

        return petDto;
    }

    @Override
    public boolean deletePet(Long petId) {
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new PetNotFoundException("Pet not found with id: " + petId));

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        if (!userRepository.existsByEmailAndPetsContains(email, pet)) {
            throw new IllegalArgumentException("You dont have pet with id: " + petId);
        }

        petRepository.delete(pet);
        log.info("Pet with id {} was deleted.", pet.getId());

        return true;
    }
}
