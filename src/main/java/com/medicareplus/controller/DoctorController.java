package com.medicareplus.controller;

import com.medicareplus.dto.AppointmentDTO;
import com.medicareplus.dto.DoctorProfileDTO;
import com.medicareplus.dto.DoctorProfileRequest;
import com.medicareplus.entity.AppointmentStatus;
import com.medicareplus.service.AppointmentService;
import com.medicareplus.service.DoctorProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    
    @Autowired
    private AppointmentService appointmentService;
    
    @Autowired
    private DoctorProfileService doctorProfileService;
    
    @GetMapping("/appointments")
    public ResponseEntity<List<AppointmentDTO>> getMyAppointments() {
        return ResponseEntity.ok(appointmentService.getMyAppointments());
    }
    
    @PutMapping("/appointments/{id}/approve")
    public ResponseEntity<AppointmentDTO> approveAppointment(@PathVariable Long id) {
        return ResponseEntity.ok(appointmentService.updateAppointmentStatus(id, AppointmentStatus.APPROVED));
    }
    
    @PutMapping("/appointments/{id}/reject")
    public ResponseEntity<AppointmentDTO> rejectAppointment(@PathVariable Long id) {
        return ResponseEntity.ok(appointmentService.updateAppointmentStatus(id, AppointmentStatus.REJECTED));
    }
    
    // Doctor Profile Management
    @PostMapping("/profile")
    public ResponseEntity<DoctorProfileDTO> createOrUpdateProfile(@Valid @RequestBody DoctorProfileRequest request) {
        return ResponseEntity.ok(doctorProfileService.createOrUpdateProfile(request));
    }
    
    @GetMapping("/profile")
    public ResponseEntity<DoctorProfileDTO> getMyProfile() {
        return ResponseEntity.ok(doctorProfileService.getMyProfile());
    }
}
