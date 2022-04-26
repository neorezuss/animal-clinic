package com.example.animal.clinic.back.entity;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "medical_services")
@Data
public class MedicalService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="medical_service_type_id")
    private MedicalServiceType medicalServiceType;
    @ManyToOne
    @JoinColumn(name="specialist_id")
    private Specialist specialist;
    @ManyToOne
    @JoinColumn(name="pet_id")
    private Pet pet;
    @Basic
    private Date date;
    @Basic
    private Time time;
    private String result;
}
