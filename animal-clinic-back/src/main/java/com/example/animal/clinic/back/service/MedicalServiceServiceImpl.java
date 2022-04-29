package com.example.animal.clinic.back.service;

import com.example.animal.clinic.back.dto.MedicalServicePriceDto;
import com.example.animal.clinic.back.dto.MedicalServiceTypeDto;
import com.example.animal.clinic.back.entity.MedicalServicePrice;
import com.example.animal.clinic.back.entity.MedicalServiceType;
import com.example.animal.clinic.back.repository.MedicalServiceTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicalServiceServiceImpl implements MedicalServiceService {
    private final MedicalServiceTypeRepository medicalServiceTypeRepository;

    @Override
    public List<MedicalServiceTypeDto> getMedicalServiceTypes() {
        return medicalServiceTypeRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
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
}
