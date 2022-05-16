package com.example.animal.clinic.back.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
@Builder
public class MedicalServiceTypeDto {
    private String name;
    private String image;
    private String shortDescription;
    private String longDescription;
    private Set<MedicalServicePriceDto> medicalServicePrices;
}
