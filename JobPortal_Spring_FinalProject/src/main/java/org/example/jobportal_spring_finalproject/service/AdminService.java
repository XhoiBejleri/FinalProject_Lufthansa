package org.example.jobportal_spring_finalproject.service;

import org.example.jobportal_spring_finalproject.mapper.UserMapper;
import org.example.jobportal_spring_finalproject.model.dto.UserDTO;
import org.example.jobportal_spring_finalproject.model.entity.User;
import org.example.jobportal_spring_finalproject.model.enums.Role;
import org.example.jobportal_spring_finalproject.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public AdminService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public Page<UserDTO> getAllUsers(Role role, Pageable pageable) {
        Page<User> users;
        if (role != null) {
            users = userRepository.findByRole(role, pageable);
        } else {
            users = userRepository.findAll(pageable);
        }
        return users.map(userMapper::toUserDTO);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
