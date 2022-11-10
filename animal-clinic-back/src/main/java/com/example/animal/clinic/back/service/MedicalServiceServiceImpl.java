package com.example.animal.clinic.back.service;

import com.example.animal.clinic.back.dto.MedicalServicePriceDto;
import com.example.animal.clinic.back.dto.MedicalServiceScheduleDto;
import com.example.animal.clinic.back.dto.MedicalServiceTypeDto;
import com.example.animal.clinic.back.dto.ServiceAppointmentDto;
import com.example.animal.clinic.back.dto.SpecialistDto;
import com.example.animal.clinic.back.entity.MedicalService;
import com.example.animal.clinic.back.entity.MedicalServicePrice;
import com.example.animal.clinic.back.entity.MedicalServiceType;
import com.example.animal.clinic.back.entity.Pet;
import com.example.animal.clinic.back.entity.Specialist;
import com.example.animal.clinic.back.exception.MedicalServiceAppointmentIsAlreadyTakenException;
import com.example.animal.clinic.back.exception.MedicalServiceNotFoundException;
import com.example.animal.clinic.back.exception.PetNotFoundException;
import com.example.animal.clinic.back.repository.MedicalServiceRepository;
import com.example.animal.clinic.back.repository.MedicalServiceTypeRepository;
import com.example.animal.clinic.back.repository.PetRepository;
import com.example.animal.clinic.back.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
@Transactional
@RequiredArgsConstructor
public class MedicalServiceServiceImpl implements MedicalServiceService {
    private final MedicalServiceTypeRepository medicalServiceTypeRepository;
    private final MedicalServiceRepository medicalServiceRepository;
    private final UserRepository userRepository;
    private final PetRepository petRepository;

    @Override
    public List<MedicalServiceTypeDto> getMedicalServiceTypes() {
        return medicalServiceTypeRepository.findAll(Sort.by("name")).stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<MedicalServiceScheduleDto> getMedicalServiceSchedulesByName(String name) {
        return medicalServiceRepository
                .findAllByMedicalServiceTypeNameAndPetIsNullAndDateAfterOrderByDateAscTimeAsc(name, new Date())
                .stream()
                .map(this::convertToScheduleDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getMedicalServiceTypeNames() {
        return medicalServiceTypeRepository.findAll(Sort.by("name")).stream().map(MedicalServiceType::getName).collect(Collectors.toList());
    }

    @Override
    public ServiceAppointmentDto makeMedicalServiceAppointment(ServiceAppointmentDto serviceAppointmentDto) {
        MedicalService medicalService = medicalServiceRepository.findById(serviceAppointmentDto.getMedicalServiceId())
                .orElseThrow(() -> new MedicalServiceNotFoundException(
                        "Medical service not found with id: " + serviceAppointmentDto.getMedicalServiceId()));

        if (nonNull(medicalService.getPet())) {
            throw new MedicalServiceAppointmentIsAlreadyTakenException(
                    "Medical service appointment with id: " + serviceAppointmentDto.getMedicalServiceId() +
                            " is already taken");
        }

        Pet pet = petRepository.findById(serviceAppointmentDto.getPetId())
                .orElseThrow(() -> new PetNotFoundException("Pet not found with id: " + serviceAppointmentDto.getPetId()));

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        if (!userRepository.existsByEmailAndPetsContains(email, pet)) {
            throw new IllegalArgumentException("User doesnt have pet with id: " + serviceAppointmentDto.getPetId());
        }

        medicalService.setPet(pet);
        medicalServiceRepository.save(medicalService);

        return serviceAppointmentDto;
    }

    @Override
    public ServiceAppointmentDto cancelMedicalServiceAppointment(ServiceAppointmentDto serviceAppointmentDto) {
        MedicalService medicalService = medicalServiceRepository.findById(serviceAppointmentDto.getMedicalServiceId())
                .orElseThrow(() -> new MedicalServiceNotFoundException(
                        "Medical service not found with id: " + serviceAppointmentDto.getMedicalServiceId()));

        if (!medicalService.getDate().after(new Date())) {
            throw new IllegalArgumentException("You cant cancel this appointment");
        }

        if (isNull(medicalService.getPet()) || medicalService.getPet().getId() != serviceAppointmentDto.getPetId()) {
            throw new IllegalArgumentException(
                    "Pet with id: " + serviceAppointmentDto.getPetId() +
                            " doesnt have appointment with id: " + serviceAppointmentDto.getMedicalServiceId());
        }

        medicalService.setPet(null);
        medicalServiceRepository.save(medicalService);

        return serviceAppointmentDto;
    }

    private MedicalServiceTypeDto convertToDto(MedicalServiceType medicalServiceType) {
        return MedicalServiceTypeDto.builder()
                .name(medicalServiceType.getName())
                .image(medicalServiceType.getImage())
                .shortDescription(medicalServiceType.getShortDescription())
                .longDescription(medicalServiceType.getLongDescription())
                .medicalServicePrices(medicalServiceType.getMedicalServicePrices()
                        .stream().map(this::convertToDto).collect(Collectors.toSet()))
                .build();
    }

    private MedicalServicePriceDto convertToDto(MedicalServicePrice medicalServicePrice) {
        return MedicalServicePriceDto.builder()
                .petType(medicalServicePrice.getPetType().getName().name())
                .price(medicalServicePrice.getPrice())
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

    private MedicalServiceScheduleDto convertToScheduleDto(MedicalService medicalService) {
        return MedicalServiceScheduleDto.builder()
                .id(medicalService.getId())
                .specialist(convertToDto(medicalService.getSpecialist()))
                .availablePetTypes(medicalService.getMedicalServiceType().getMedicalServicePrices()
                        .stream().map(item -> item.getPetType().getName().name()).collect(Collectors.toSet()))
                .date(medicalService.getDate())
                .time(medicalService.getTime())
                .officeNumber(medicalService.getOfficeNumber())
                .build();
    }
}
