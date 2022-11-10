package com.example.animal.clinic.back.service;


import com.example.animal.clinic.back.entity.Password;
import com.example.animal.clinic.back.entity.User;
import com.example.animal.clinic.back.repository.PasswordRepository;
import com.example.animal.clinic.back.repository.UserRepository;
import com.example.animal.clinic.back.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;


@Service
@Transactional
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordRepository passwordRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with email: " + email));
        Password password = passwordRepository.findByUserEmail(email);
        return fromUserEntityToUserDetails(user, password);
    }

    private UserDetails fromUserEntityToUserDetails(User user, Password password) {
        return UserDetailsImpl.builder()
                .email(user.getEmail())
                .password(password.getPassword())
                .enabled(user.isEnabled())
                .grantedAuthorities(Collections.singletonList(new SimpleGrantedAuthority(user.getRoles().toString())))
                .build();
    }
}
