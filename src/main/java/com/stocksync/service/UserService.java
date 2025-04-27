package com.stocksync.service;

import com.stocksync.dto.SigninRequest;
import com.stocksync.dto.SigninResponse;
import com.stocksync.dto.UserDTO;
import com.stocksync.entity.User;
import com.stocksync.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDTO createUser(UserDTO userDTO) {
        if (userRepository.existsByUserEmailId(userDTO.getUserEmailId())) {
            throw new RuntimeException("Email already exists");
        }
        
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        user.setUserPassword(passwordEncoder.encode(userDTO.getUserPassword()));
        user.setUserStatus("ACTIVE");
        
        User savedUser = userRepository.save(user);
        UserDTO responseDTO = new UserDTO();
        BeanUtils.copyProperties(savedUser, responseDTO);
        return responseDTO;
    }

    public SigninResponse signin(SigninRequest request) {
        User user = userRepository.findByUserEmailId(request.getUserEmailId())
            .orElseThrow(() -> new RuntimeException("User not found"));
            
        if (!passwordEncoder.matches(request.getUserPassword(), user.getUserPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        
        // Generate JWT token here
        String token = "dummy-token"; // Replace with actual JWT token generation
        
        SigninResponse response = new SigninResponse();
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        response.setToken(token);
        response.setUser(userDTO);
        return response;
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));
            
        user.setUserName(userDTO.getUserName());
        user.setUserEmailId(userDTO.getUserEmailId());
        if (userDTO.getUserPassword() != null) {
            user.setUserPassword(passwordEncoder.encode(userDTO.getUserPassword()));
        }
        
        User updatedUser = userRepository.save(user);
        UserDTO responseDTO = new UserDTO();
        BeanUtils.copyProperties(updatedUser, responseDTO);
        return responseDTO;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public UserDTO getUser(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));
            
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
            .map(user -> {
                UserDTO dto = new UserDTO();
                BeanUtils.copyProperties(user, dto);
                return dto;
            })
            .collect(Collectors.toList());
    }
}
