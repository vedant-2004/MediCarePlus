package com.medicareplus.service;

import com.medicareplus.dto.UserDTO;
import com.medicareplus.entity.Role;
import com.medicareplus.entity.User;
import com.medicareplus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public List<UserDTO> getDoctors() {
        return userRepository.findByRole(Role.DOCTOR).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    private UserDTO convertToDTO(User user) {
        return new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getRole(), user.isEnabled());
    }
}
