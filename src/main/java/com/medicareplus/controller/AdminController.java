package com.medicareplus.controller;

import com.medicareplus.dto.AppointmentDTO;
import com.medicareplus.dto.UserDTO;
import com.medicareplus.service.AppointmentService;
import com.medicareplus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private AppointmentService appointmentService;
    
    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
    
    @GetMapping("/appointments")
    public ResponseEntity<List<AppointmentDTO>> getAllAppointments() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }
}
