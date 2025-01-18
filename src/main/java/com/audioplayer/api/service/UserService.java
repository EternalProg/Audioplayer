package com.audioplayer.api.service;

import com.audioplayer.api.DTO.UserCreateDTO;
import com.audioplayer.api.DTO.UserDTO;
import com.audioplayer.api.model.User;
import com.audioplayer.api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());
    }

    public Optional<UserDTO> getUserByUsername(String username) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        return userOpt.map(UserDTO::new);
    }

    public Optional<UserDTO> getUserById(Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        return userOpt.map(UserDTO::new);
    }

    public Optional<UserDTO> createUser(UserCreateDTO userDTO) {
        // потрібно додати хешування пароля перед збереженням
        User user = new User(userDTO);
        return Optional.of(new UserDTO(userRepository.save(user)));
    }

    public Optional<UserDTO> updateUser(Long userId, UserCreateDTO userDTO) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setAll(userDTO);
            return Optional.of(new UserDTO(userRepository.save(user)));
        }
        System.out.println("User not found");
        return Optional.empty();
    }

    public boolean deleteUser(Long userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
            System.out.println("User deleted");
            return true;
        }
        System.out.println("User not found");
        return false;
    }

    public boolean deleteUser(String username) {
        if (userRepository.existsByUsername(username)) {
            userRepository.deleteByUsername(username);
            System.out.println("User deleted");
            return true;
        }
        System.out.println("User not found");
        return false;
    }
}
