package com.example.animal.clinic.back.service;

import com.example.animal.clinic.back.dto.PetDto;
import com.example.animal.clinic.back.dto.ProfileDto;
import com.example.animal.clinic.back.entity.Pet;
import com.example.animal.clinic.back.entity.User;
import com.example.animal.clinic.back.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public ProfileDto getUserProfile() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return ProfileDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .patronymic(user.getPatronymic())
                .gender(user.getGender())
                .birthDate(user.getBirthDate())
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .pets(user.getPets().stream().map(this::convertToDto).collect(Collectors.toSet()))
                .build();
    }

    @Override
    public ProfileDto updateUserProfile(ProfileDto profileDto) {
        User user = userRepository.findByEmail(profileDto.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + profileDto.getEmail()));

        user.setFirstName(profileDto.getFirstName());
        user.setLastName(profileDto.getLastName());
        user.setPatronymic(profileDto.getPatronymic());
        user.setGender(profileDto.getGender());
        user.setBirthDate(profileDto.getBirthDate());
        user.setPhoneNumber(profileDto.getPhoneNumber());

        userRepository.save(user);
        log.info("User profile with email {} was updated.", user.getEmail());
        return profileDto;
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
}
