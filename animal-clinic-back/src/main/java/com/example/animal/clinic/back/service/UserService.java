package com.example.animal.clinic.back.service;

import com.example.animal.clinic.back.dto.ProfileDto;

public interface UserService {
    ProfileDto getUserProfile();

    ProfileDto updateUserProfile(ProfileDto profileDto);
}
