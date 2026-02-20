package com.medicareplus.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DoctorProfileRequest {
    
    @NotBlank(message = "Specialization is required")
    private String specialization;
    
    @NotBlank(message = "Degree is required")
    private String degree;
    
    @NotNull(message = "Experience years is required")
    @Min(value = 0, message = "Experience years must be positive")
    private Integer experienceYears;
    
    @NotNull(message = "Consultation fee is required")
    @Min(value = 0, message = "Consultation fee must be positive")
    private Double consultationFee;
    
    private String about;
    
    private String clinicAddress;
    
    private String phoneNumber;
    
    private Boolean availableForConsultation = true;
}
