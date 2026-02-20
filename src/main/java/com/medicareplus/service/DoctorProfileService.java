package com.medicareplus.service;

import com.medicareplus.dto.DoctorProfileDTO;
import com.medicareplus.dto.DoctorProfileRequest;
import com.medicareplus.entity.DoctorProfile;
import com.medicareplus.entity.Role;
import com.medicareplus.entity.User;
import com.medicareplus.exception.ResourceNotFoundException;
import com.medicareplus.repository.DoctorProfileRepository;
import com.medicareplus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorProfileService {
    
    @Autowired
    private DoctorProfileRepository doctorProfileRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    public DoctorProfileDTO createOrUpdateProfile(DoctorProfileRequest request) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User doctor = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));
        
        if (doctor.getRole() != Role.DOCTOR) {
            throw new IllegalArgumentException("Only doctors can create profiles");
        }
        
        DoctorProfile profile = doctorProfileRepository.findByUser(doctor)
                .orElse(new DoctorProfile());
        
        profile.setUser(doctor);
        profile.setSpecialization(request.getSpecialization());
        profile.setDegree(request.getDegree());
        profile.setExperienceYears(request.getExperienceYears());
        profile.setConsultationFee(request.getConsultationFee());
        profile.setAbout(request.getAbout());
        profile.setClinicAddress(request.getClinicAddress());
        profile.setPhoneNumber(request.getPhoneNumber());
        profile.setAvailableForConsultation(request.getAvailableForConsultation());
        
        DoctorProfile saved = doctorProfileRepository.save(profile);
        return convertToDTO(saved);
    }
    
    public DoctorProfileDTO getMyProfile() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User doctor = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));
        
        DoctorProfile profile = doctorProfileRepository.findByUser(doctor)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found. Please create your profile first."));
        
        return convertToDTO(profile);
    }
    
    public DoctorProfileDTO getProfileById(Long profileId) {
        DoctorProfile profile = doctorProfileRepository.findById(profileId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor profile not found"));
        
        return convertToDTO(profile);
    }
    
    public List<DoctorProfileDTO> getAllDoctorProfiles() {
        return doctorProfileRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    private DoctorProfileDTO convertToDTO(DoctorProfile profile) {
        return new DoctorProfileDTO(
            profile.getId(),
            profile.getUser().getId(),
            profile.getUser().getName(),
            profile.getUser().getEmail(),
            profile.getSpecialization(),
            profile.getDegree(),
            profile.getExperienceYears(),
            profile.getConsultationFee(),
            profile.getAbout(),
            profile.getClinicAddress(),
            profile.getPhoneNumber(),
            profile.getAvailableForConsultation()
        );
    }
}
