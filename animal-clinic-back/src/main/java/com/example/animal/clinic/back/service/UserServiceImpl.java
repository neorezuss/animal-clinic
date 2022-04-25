package com.example.animal.clinic.back.service;

import com.example.animal.clinic.back.dto.ProfileDto;
import com.example.animal.clinic.back.entity.User;
import com.example.animal.clinic.back.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public ProfileDto getUserProfile() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info(email);
        User user = userRepository.findByEmail(email).
                orElseThrow(() ->
                        new UsernameNotFoundException("User not found with email: " + email));
        return ProfileDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .patronymic(user.getPatronymic())
                .gender(user.getGender())
                .birthDate(user.getBirthDate())
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .build();
    }
}
