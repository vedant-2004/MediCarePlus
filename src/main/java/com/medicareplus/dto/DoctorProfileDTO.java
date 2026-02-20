package com.medicareplus.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorProfileDTO {
    private Long id;
    private Long userId;
    private String doctorName;
    private String doctorEmail;
    private String specialization;
    private String degree;
    private Integer experienceYears;
    private Double consultationFee;
    private String about;
    private String clinicAddress;
    private String phoneNumber;
    private Boolean availableForConsultation;
}
