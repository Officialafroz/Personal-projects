package com.elevata.gsrtc.controller;

import com.elevata.gsrtc.dto.UserDTO;
import com.elevata.gsrtc.entity.User;
import com.elevata.gsrtc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getUsersList() {
        return userService.getUsersList();
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable int userId) {
        return userService.findById(userId);
    }

    @PostMapping("/add")
    @Transactional
    public void addUser(@RequestBody UserDTO userDTO) {
        userService.save(userDTO);
    }

    @DeleteMapping("/delete/{userId}")
    @Transactional
    public String deleteUser(@PathVariable int userId) {
        User user = userService.findById(userId);

        if (user == null) {
            throw new RuntimeException("User not found for id");
        }

        userService.deleteById(userId);
        return String.format("User for id %d is deleted.", userId);
    }
}
