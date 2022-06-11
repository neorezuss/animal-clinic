package com.example.animal.clinic.back.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "medical_service_prices")
@Data
public class MedicalServicePrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "medical_service_type_id")
    private MedicalServiceType medicalServiceType;
    @ManyToOne
    @JoinColumn(name = "pet_type_id")
    private PetType petType;
    private BigDecimal price;
}