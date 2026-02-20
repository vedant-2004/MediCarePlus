package com.medicareplus.service;

import com.medicareplus.dto.AppointmentDTO;
import com.medicareplus.dto.AppointmentRequest;
import com.medicareplus.dto.UserDTO;
import com.medicareplus.entity.Appointment;
import com.medicareplus.entity.AppointmentStatus;
import com.medicareplus.entity.Role;
import com.medicareplus.entity.User;
import com.medicareplus.exception.ResourceNotFoundException;
import com.medicareplus.repository.AppointmentRepository;
import com.medicareplus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService {
    
    @Autowired
    private AppointmentRepository appointmentRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    public AppointmentDTO bookAppointment(AppointmentRequest request) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User patient = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));
        
        User doctor = userRepository.findById(request.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));
        
        if (doctor.getRole() != Role.DOCTOR) {
            throw new IllegalArgumentException("Selected user is not a doctor");
        }
        
        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setAppointmentDate(request.getAppointmentDate());
        appointment.setStatus(AppointmentStatus.PENDING);
        
        Appointment saved = appointmentRepository.save(appointment);
        return convertToDTO(saved);
    }
    
    public List<AppointmentDTO> getMyAppointments() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        List<Appointment> appointments;
        if (user.getRole() == Role.PATIENT) {
            appointments = appointmentRepository.findByPatient(user);
        } else if (user.getRole() == Role.DOCTOR) {
            appointments = appointmentRepository.findByDoctor(user);
        } else {
            appointments = appointmentRepository.findAll();
        }
        
        return appointments.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public List<AppointmentDTO> getAllAppointments() {
        return appointmentRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public AppointmentDTO updateAppointmentStatus(Long id, AppointmentStatus status) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));
        
        appointment.setStatus(status);
        Appointment updated = appointmentRepository.save(appointment);
        return convertToDTO(updated);
    }
    
    private AppointmentDTO convertToDTO(Appointment appointment) {
        UserDTO patientDTO = new UserDTO(
            appointment.getPatient().getId(),
            appointment.getPatient().getName(),
            appointment.getPatient().getEmail(),
            appointment.getPatient().getRole(),
            appointment.getPatient().isEnabled()
        );
        
        UserDTO doctorDTO = new UserDTO(
            appointment.getDoctor().getId(),
            appointment.getDoctor().getName(),
            appointment.getDoctor().getEmail(),
            appointment.getDoctor().getRole(),
            appointment.getDoctor().isEnabled()
        );
        
        return new AppointmentDTO(
            appointment.getId(),
            patientDTO,
            doctorDTO,
            appointment.getAppointmentDate(),
            appointment.getStatus()
        );
    }
}
