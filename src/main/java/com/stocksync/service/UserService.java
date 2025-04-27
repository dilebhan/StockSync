package com.stocksync.service;

import com.stocksync.dto.SigninRequest;
import com.stocksync.dto.SigninResponse;
import com.stocksync.dto.UserDTO;
import com.stocksync.entity.User;
import com.stocksync.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO createUser(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        user.setUserStatus("ACTIVE");
        User savedUser = userRepository.save(user);
        UserDTO responseDTO = new UserDTO();
        BeanUtils.copyProperties(savedUser, responseDTO);
        return responseDTO;
    }

    public SigninResponse signin(SigninRequest request) {
        User user = userRepository.findByUserEmailId(request.getUserEmailId())
            .orElseThrow(() -> new RuntimeException("User not found"));
            
        if (!user.getUserPassword().equals(request.getUserPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        
        SigninResponse response = new SigninResponse();
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        response.setUser(userDTO);
        response.setToken("dummy-token"); // Replace with actual JWT token
        return response;
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));
            
        BeanUtils.copyProperties(userDTO, user);
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
