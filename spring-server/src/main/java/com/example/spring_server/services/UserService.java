package com.example.spring_server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring_server.dto.requests.UserDTO;
import com.example.spring_server.entities.User;
import com.example.spring_server.exceptions.user.UserAlreadyExistsException;
import com.example.spring_server.exceptions.user.UserNotFoundException;
import com.example.spring_server.repositories.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " does not exist"));
    }

    public User createUser(UserDTO user) {
        boolean userExists = userRepository.existsByUsername(user.getUsername());
        if (userExists) {
            throw new UserAlreadyExistsException("User with username " + user.getUsername() + " already exists");
        }
        User newUser = new User(user);
        return userRepository.save(newUser);
    }

    public void deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }

    public User getUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("User with username " + username + " does not exist");
        }
        return user;
    }

    public User updateUser(Long id, UserDTO userDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        // Update fields if present in the DTO (null values are ignored)
        if (userDTO.getUsername() != null) {
            existingUser.setUsername(userDTO.getUsername());
        }
        if (userDTO.getEmail() != null) {
            existingUser.setEmail(userDTO.getEmail());
        }
        if (userDTO.getRole() != null) {
            existingUser.setRole(userDTO.getRole());
        }
        if (userDTO.getPosition() != null) {
            existingUser.setPosition(userDTO.getPosition());
        }
        if (userDTO.getStartedPlaying() != null) {
            existingUser.setStartedPlaying(userDTO.getStartedPlaying());
        }

        return userRepository.save(existingUser);
    }
}
