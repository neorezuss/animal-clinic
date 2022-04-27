package com.example.animal.clinic.back.controller;

import com.example.animal.clinic.back.dto.ProfileDto;
import com.example.animal.clinic.back.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/profile")
    public ProfileDto getUserProfile() {
        return userService.getUserProfile();
    }

    @PostMapping
    public ProfileDto updateUserProfile(ProfileDto profileDto) {
        return userService.updateUserProfile(profileDto);
    }
}
