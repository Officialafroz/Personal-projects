package com.elevata.gsrtc.service;

import com.elevata.gsrtc.dto.UserDTO;
import com.elevata.gsrtc.repository.UserRepository;
import com.elevata.gsrtc.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsersList() {
        List<User> users = userRepository.findAll();

        if (users == null) {
            throw new RuntimeException("There are no users found in database.");
        }
        return users;
    }

    public void save(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setGender(userDTO.getGender());

        userRepository.save(user);
        System.out.println("User has been added.");
    }

    public void deleteById(int userId) {
        userRepository.deleteById(userId);
    }

    public User findById(int userId) {
        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()) {
            return user.get();
        } else {
            throw new RuntimeException("User not found for id");
        }
    }


    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }
}
