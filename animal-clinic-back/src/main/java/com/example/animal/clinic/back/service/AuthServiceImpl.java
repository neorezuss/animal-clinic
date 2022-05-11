package com.example.animal.clinic.back.service;

import com.example.animal.clinic.back.dto.EmailDto;
import com.example.animal.clinic.back.dto.LoginDto;
import com.example.animal.clinic.back.dto.RefreshTokenDto;
import com.example.animal.clinic.back.dto.RegistrationDto;
import com.example.animal.clinic.back.dto.ChangePasswordDto;
import com.example.animal.clinic.back.entity.Password;
import com.example.animal.clinic.back.entity.Role;
import com.example.animal.clinic.back.entity.RoleEnum;
import com.example.animal.clinic.back.entity.User;
import com.example.animal.clinic.back.repository.PasswordRepository;
import com.example.animal.clinic.back.repository.RoleRepository;
import com.example.animal.clinic.back.repository.UserRepository;
import com.example.animal.clinic.back.security.AuthResponse;
import com.example.animal.clinic.back.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordRepository passwordRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final EmailSenderService emailSenderService;

    @Override
    public AuthResponse authenticateUser(LoginDto loginDto) {
        User user = findByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword());

        String accessToken = jwtProvider.generateAccessToken(user.getEmail(), user.getRoles());
        String refreshToken = jwtProvider.generateRefreshToken(user.getEmail());
        log.info("User with email {} was authenticated.", user.getEmail());

        return new AuthResponse(accessToken, refreshToken);
    }

    @Override
    public RegistrationDto registerUser(RegistrationDto registrationDto) {
        if (userRepository.existsByEmail(registrationDto.getEmail())) {
            throw new IllegalArgumentException("Unable to create. Email is already taken!");
        }
        return saveUser(registrationDto);
    }

    @Override
    public AuthResponse refreshTokens(RefreshTokenDto refreshTokenDto) {
        jwtProvider.validateToken(refreshTokenDto.getRefreshToken());
        String email = jwtProvider.getEmailFromToken(refreshTokenDto.getRefreshToken());

        User user = findByEmail(email);

        String accessToken = jwtProvider.generateAccessToken(user.getEmail(), user.getRoles());
        String refreshToken = jwtProvider.generateRefreshToken(user.getEmail());
        log.info("Tokens for user with email {} was refreshed.", email);

        return new AuthResponse(accessToken, refreshToken);
    }

    @Override
    public boolean resetPassword(EmailDto emailDto) {
        if (!userRepository.existsByEmail(emailDto.getEmail())) {
            throw new IllegalArgumentException("User with email " + emailDto.getEmail() + " doesnt exist.");
        }

        String newPassword = generateRandomPassword(16);

        Password password = passwordRepository.findByUserEmail(emailDto.getEmail());
        password.setPassword(passwordEncoder.encode(newPassword));
        passwordRepository.save(password);

        new Thread(() -> emailSenderService.sendPasswordRecoveryEmail(
                emailDto.getEmail(),
                newPassword)).start();

        log.info("User with email {} reset password.", emailDto.getEmail());
        return true;
    }

    @Override
    public boolean changePassword(ChangePasswordDto changePasswordDto) {
        User user = findByEmailAndPassword(changePasswordDto.getEmail(), changePasswordDto.getOldPassword());

        Password password = passwordRepository.findByUserEmail(changePasswordDto.getEmail());

        password.setPassword(passwordEncoder.encode(changePasswordDto.getNewPassword()));
        passwordRepository.save(password);

        new Thread(() -> emailSenderService.sendPasswordRecoveryEmail(
                changePasswordDto.getEmail(),
                changePasswordDto.getNewPassword())).start();

        log.info("User with email {} changed password.", changePasswordDto.getEmail());

        return true;
    }

    private RegistrationDto saveUser(RegistrationDto registrationDto) {
        Role defaultRole = roleRepository.getRoleByName(RoleEnum.ROLE_USER);

        User user = User.builder()
                .firstName(registrationDto.getFirstName())
                .lastName(registrationDto.getLastName())
                .patronymic(registrationDto.getPatronymic())
                .gender(registrationDto.getGender())
                .birthDate(registrationDto.getBirthDate())
                .phoneNumber(registrationDto.getPhoneNumber())
                .email(registrationDto.getEmail())
                .enabled(true)
                .role(defaultRole)
                .build();

        Password password = Password.builder()
                .user(user)
                .password(passwordEncoder.encode(registrationDto.getPassword()))
                .build();

        passwordRepository.save(password);

        new Thread(() -> emailSenderService.sendRegistrationEmail(
                registrationDto.getEmail(),
                registrationDto.getFirstName(),
                registrationDto.getPassword())).start();

        log.info("User with email {} was registered.", user.getEmail());
        return registrationDto;
    }

    private User findByEmailAndPassword(String email, String password) {
        User user = findByEmail(email);
        Password userPassword = passwordRepository.findByUserEmail(email);

        if (!passwordEncoder.matches(password, userPassword.getPassword())) {
            throw new IllegalArgumentException("Invalid username or password!");
        }

        return user;
    }

    private User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }

    private String generateRandomPassword(int len) {
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(len);

        for (int i = 0; i < len; i++)
            sb.append(chars.charAt(rnd.nextInt(chars.length())));

        return sb.toString();
    }
}
