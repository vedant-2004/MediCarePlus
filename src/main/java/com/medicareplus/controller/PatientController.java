package com.medicareplus.controller;

import com.medicareplus.dto.AppointmentDTO;
import com.medicareplus.dto.AppointmentRequest;
import com.medicareplus.dto.DoctorProfileDTO;
import com.medicareplus.dto.UserDTO;
import com.medicareplus.service.AppointmentService;
import com.medicareplus.service.DoctorProfileService;
import com.medicareplus.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private AppointmentService appointmentService;
    
    @Autowired
    private DoctorProfileService doctorProfileService;
    
    @GetMapping("/doctors")
    public ResponseEntity<List<UserDTO>> getDoctors() {
        return ResponseEntity.ok(userService.getDoctors());
    }
    
    @GetMapping("/doctors/profiles")
    public ResponseEntity<List<DoctorProfileDTO>> getDoctorProfiles() {
        return ResponseEntity.ok(doctorProfileService.getAllDoctorProfiles());
    }
    
    @GetMapping("/doctors/profiles/{id}")
    public ResponseEntity<DoctorProfileDTO> getDoctorProfile(@PathVariable Long id) {
        return ResponseEntity.ok(doctorProfileService.getProfileById(id));
    }
    
    @PostMapping("/appointments")
    public ResponseEntity<AppointmentDTO> bookAppointment(@Valid @RequestBody AppointmentRequest request) {
        return ResponseEntity.ok(appointmentService.bookAppointment(request));
    }
    
    @GetMapping("/appointments")
    public ResponseEntity<List<AppointmentDTO>> getMyAppointments() {
        return ResponseEntity.ok(appointmentService.getMyAppointments());
    }
}
