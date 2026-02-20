package com.medicareplus.dto;

import com.medicareplus.entity.AppointmentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDTO {
    private Long id;
    private UserDTO patient;
    private UserDTO doctor;
    private LocalDateTime appointmentDate;
    private AppointmentStatus status;
}
