package com.medicareplus.dto;

import com.medicareplus.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private String email;
    private String name;
    private Role role;
}
