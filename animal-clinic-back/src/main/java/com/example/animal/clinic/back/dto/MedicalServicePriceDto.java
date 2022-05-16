package com.example.animal.clinic.back.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class MedicalServicePriceDto {
    private String petType;
    private BigDecimal price;
}
