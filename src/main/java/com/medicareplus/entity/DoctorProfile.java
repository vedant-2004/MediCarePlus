package com.medicareplus.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "doctor_profiles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorProfile {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private User user;
    
    @Column(nullable = false)
    private String specialization;
    
    @Column(nullable = false)
    private String degree;
    
    @Column(nullable = false)
    private Integer experienceYears;
    
    @Column(nullable = false)
    private Double consultationFee;
    
    @Column(length = 1000)
    private String about;
    
    @Column
    private String clinicAddress;
    
    @Column
    private String phoneNumber;
    
    @Column
    private Boolean availableForConsultation = true;
}
