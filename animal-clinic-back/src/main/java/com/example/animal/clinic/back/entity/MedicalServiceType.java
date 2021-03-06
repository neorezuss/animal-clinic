package com.example.animal.clinic.back.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "medical_service_types")
@Data
public class MedicalServiceType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String image;
    private String shortDescription;
    private String longDescription;
    @OneToMany(mappedBy = "medicalServiceType")
    private Set<MedicalServicePrice> medicalServicePrices;
}
