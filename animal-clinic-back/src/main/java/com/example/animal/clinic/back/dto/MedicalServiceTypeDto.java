package com.example.animal.clinic.back.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MedicalServiceTypeDto {
    private String name;
    private Byte[] image;
    private String shortDescription;
    private String longDescription;
}
